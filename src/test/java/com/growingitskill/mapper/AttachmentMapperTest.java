package com.growingitskill.mapper;

import java.util.List;

import javax.naming.NamingException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.growingitskill.config.MybatisJndiConfig;
import com.growingitskill.domain.AttachmentVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MybatisJndiConfig.class)
public class AttachmentMapperTest {

	private static final Logger logger = LoggerFactory.getLogger(CategoryMapperTest.class);

	@BeforeClass
	public static void jndiBind() throws NamingException {
		SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=UTF-8");
		dataSource.setUsername("test");
		dataSource.setPassword("test");
		builder.bind("jdbc/test", dataSource);
	}
	
	@Autowired
	private AttachmentMapper attachmentMapper;
	
	@Test
	public void insertAttachment() throws Exception {
		AttachmentVO attachmentVO = new AttachmentVO();
		attachmentVO.setFullName("ㅏㅏㅏㅏoop");
		
		attachmentMapper.create(attachmentVO);
		
		logger.info("id: " + attachmentVO.getId());
	}
	
	@Test
	public void selectAttachmentById() throws Exception {
		long id = 18;

		AttachmentVO attachmentVO = attachmentMapper.readAttachmentById(id);

		logger.info(attachmentVO.toString());
	}
	
	@Test
	public void updateAlternateText() throws Exception {
		long id = 24;
		String alternateText = "al";
		
		attachmentMapper.updateAlternateTextById(id, alternateText);
	}
	
	@Test
	public void updateDescription() throws Exception {
		long id = 21;
		String description = "desc";
		
		attachmentMapper.updateDescriptionById(id, description);
	}
	
	@Test
	public void deleteAttachment() throws Exception {
		long[] ids = {22, 23, 25};
		
		attachmentMapper.deleteByIds(ids);
	}
	
	@Test
	public void selectAttachment() throws Exception {
		List<AttachmentVO> list = attachmentMapper.listAll();
		
		for (AttachmentVO attachmentVO : list) {
			logger.info(attachmentVO.toString());
		}
	}

}
