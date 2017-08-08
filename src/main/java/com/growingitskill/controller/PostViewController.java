package com.growingitskill.controller;

import java.net.URI;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.growingitskill.domain.AttachmentVO;
import com.growingitskill.domain.CategoryVO;
import com.growingitskill.domain.Criteria;
import com.growingitskill.domain.PageMaker;
import com.growingitskill.domain.PostVO;
import com.growingitskill.domain.SearchCriteria;
import com.growingitskill.mapper.CategoryRelationMapper;
import com.growingitskill.service.AttachmentService;
import com.growingitskill.service.CategoryService;
import com.growingitskill.service.MemberService;
import com.growingitskill.service.PostService;
import com.growingitskill.util.UploadFileUtils;

@Controller
@RequestMapping("/admin/post")
public class PostViewController {

	public static final Logger LOGGER = LoggerFactory.getLogger(PostViewController.class);

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private PostService postService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategoryRelationMapper categoryRelationMapper;

	@Autowired
	private AttachmentService attachmentService;

	@RequestMapping(method = RequestMethod.GET)
	public String listPage(@ModelAttribute("criteria") SearchCriteria searchCriteria, Model model) throws Exception {
		int requestPerPageNum = searchCriteria.getPerPageNum();

		if (requestPerPageNum < 20) {
			requestPerPageNum = 20;
		}

		searchCriteria.setPerPageNum(requestPerPageNum);

		LOGGER.info("list -> " + searchCriteria.toString());

		model.addAttribute("list", postService.findList(searchCriteria));
		model.addAttribute("categoryList", categoryService.listAll());

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(searchCriteria);
		pageMaker.setDisplayPageNum(10);
		pageMaker.setTotalCount(postService.countCriteria(searchCriteria));

		model.addAttribute("pageMaker", pageMaker);

		return "admin/post/list";
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public PostVO listPage(@RequestBody Map<String, Long> map) throws Exception {
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setId(map.get("categoryId"));

		PostVO postVO = new PostVO();
		postVO.setId(map.get("id"));
		postVO.setCategoryVO(categoryVO);

		categoryRelationMapper.create(postVO);

		PostVO result = postService.findById(map.get("id"));

		return result;
	}

	@RequestMapping(value = "new", method = RequestMethod.GET)
	public void newPage(@ModelAttribute Criteria criteria, Model model) throws Exception {
		model.addAttribute("categoryList", categoryService.listAll());
	}

	@RequestMapping(value = "new", method = RequestMethod.POST)
	public String newPagePOST(PostVO postVO, @RequestParam("loginId") String loginId,
			@RequestParam("categoryId") long categoryId, RedirectAttributes redirectAttributes) throws Exception {
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setId(categoryId);

		postVO.setCategoryVO(categoryVO);

		postVO.setAuthor(memberService.findIdByLoginId(loginId));

		LOGGER.info(postVO.toString());

		postService.regist(postVO);

		redirectAttributes.addFlashAttribute("msg", "success");

		return "redirect:/admin/post";
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String editPage(@PathVariable("id") long id, @ModelAttribute Criteria criteria, Model model)
			throws Exception {
		model.addAttribute(postService.findById(id));
		model.addAttribute("categoryList", categoryService.listAll());

		return "admin/post/edit";
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public String editPagePUT(PostVO postVO, @RequestParam("categoryId") long categoryId, Criteria criteria,
			RedirectAttributes redirectAttributes) throws Exception {
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setId(categoryId);

		postVO.setCategoryVO(categoryVO);

		postService.modify(postVO);

		redirectAttributes.addAttribute("page", criteria.getPage());
		redirectAttributes.addAttribute("perPageNum", criteria.getPerPageNum());
		redirectAttributes.addFlashAttribute("msg", "success");

		return "redirect:/admin/post";
	}

	/**
	 * 
	 * @param upload
	 *            CKeditor의 파일 업로드는 Request Payload의 name에 따라 MultipartFile
	 *            변수이름을 "upload"라 해준다. (다른 이름일 시 파일을 못 찾는 버그 발생)
	 * @throws Exception
	 */
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public String uploadByCKEditor(@RequestPart("upload") MultipartFile file,
			@RequestParam("CKEditorFuncNum") int number, UriComponentsBuilder uriComponentsBuilder, Model model,
			HttpServletResponse response) throws Exception {
		LOGGER.info("originalFilename: " + file.getOriginalFilename());
		LOGGER.info("contentType: " + file.getContentType());
		LOGGER.info("name: " + file.getName());
		LOGGER.info("byte: " + file.getBytes());
		LOGGER.info("size: " + file.getSize());
		LOGGER.info("number: " + number);

		String path = servletContext.getRealPath("/resources/upload");

		String fullName = UploadFileUtils.uploadFile(path, file.getOriginalFilename(), file.getBytes());

		AttachmentVO attachmentVO = new AttachmentVO();
		attachmentVO.setFullName(fullName);
		attachmentVO.setMimeType(file.getContentType());

		attachmentService.addAttachment(attachmentVO);

		URI locationUri = uriComponentsBuilder.path("/attachments/").path(String.valueOf(attachmentVO.getId())).build()
				.toUri();

		response.setHeader("Location", locationUri.toString());

		model.addAttribute("CKEditorFuncNum", number);
		model.addAttribute("fileUrl", "/resources/upload" + fullName);

		return "admin/post/upload";
	}

}
