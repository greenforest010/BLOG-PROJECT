/**
 * @license Copyright (c) 2003-2017, CKSource - Frederico Knabben. All rights
 *          reserved. For licensing, see LICENSE.md or
 *          http://ckeditor.com/license
 */

/*
 * toolbar mode generator
 * http://nightly.ckeditor.com/18-01-21-07-05/full/samples/toolbarconfigurator/index.html#basic
 */
CKEDITOR.editorConfig = function(config) {
	config.toolbarGroups = [ {
		name : 'document',
		groups : [ 'mode', 'document', 'doctools' ]
	}, {
		name : 'clipboard',
		groups : [ 'clipboard', 'undo' ]
	}, {
		name : 'editing',
		groups : [ 'find', 'selection', 'spellchecker', 'editing' ]
	}, {
		name : 'forms',
		groups : [ 'forms' ]
	}, '/', {
		name : 'basicstyles',
		groups : [ 'basicstyles', 'cleanup' ]
	}, {
		name : 'paragraph',
		groups : [ 'list', 'indent', 'blocks', 'bidi', 'paragraph' ]
	}, {
		name : 'links',
		groups : [ 'links' ]
	}, {
		name : 'insert',
		groups : [ 'insert' ]
	}, '/', {
		name : 'styles',
		groups : [ 'styles' ]
	}, {
		name : 'colors',
		groups : [ 'colors' ]
	}, {
		name : 'tools',
		groups : [ 'tools' ]
	}, {
		name : 'others',
		groups : [ 'others' ]
	}, {
		name : 'about',
		groups : [ 'about' ]
	} ];

	config.removeButtons = 'Image,Flash,Table,HorizontalRule,PageBreak,Iframe,Source,Save,NewPage,Preview,Print,Templates,Cut,Undo,Redo,Copy,Paste,PasteText,PasteFromWord,Form,Scayt,SelectAll,Find,Replace,Checkbox,Radio,TextField,Textarea,Select,Button,ImageButton,HiddenField,NumberedList,Outdent,Blockquote,BidiLtr,BidiRtl,CreateDiv,Indent,BulletedList,Language';
	
	config.height = '10em';
};
