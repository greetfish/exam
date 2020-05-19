(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-af5b9a7e"],{5525:function(t,e,s){"use strict";s.r(e);var o=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"courseManage"},[s("el-button",{staticClass:"addButton",attrs:{type:"primary",icon:"el-icon-plus",size:"small"},on:{click:function(e){return t.openAddCourseDialog()}}},[t._v("新增")]),s("el-divider"),s("el-row",[s("el-col",{attrs:{span:12,offset:6,align:"left"}},[s("el-table",{staticStyle:{width:"100%"},attrs:{data:t.courseList,stripe:""}},[s("el-table-column",{attrs:{type:"index",width:"50"}}),s("el-table-column",{attrs:{prop:"courseName",label:"科目"}}),s("el-table-column",{attrs:{prop:"",label:"",width:"240",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[s("el-button",{attrs:{size:"mini",round:""},on:{click:function(s){return t.questionEdit(e.row)}}},[t._v("题库维护")]),s("el-button",{attrs:{type:"danger",icon:"el-icon-delete",size:"mini",circle:""},on:{click:function(s){return t.courseDelete(e.row)}}})]}}])})],1)],1)],1),s("el-dialog",{attrs:{visible:t.questionEditDialog},on:{"update:visible":function(e){t.questionEditDialog=e},close:function(e){return t.questionEditDialogClose()}}},[s("span",{staticClass:"dialogTitle"},[t._v("题库编辑："+t._s(t.currCourseName))]),s("el-row",[s("el-col",{attrs:{span:22,offset:1,align:"center"}},[s("el-table",{staticStyle:{width:"560px"},attrs:{data:t.questionEditDialog_Data}},[s("el-table-column",{attrs:{label:"单选题",width:"140",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[s("a",{staticClass:"dialogTagA"},[t._v(t._s(e.row.countQuestion_A)+"条")]),s("el-button",{attrs:{size:"mini",icon:"el-icon-delete",circle:""},on:{click:function(e){return t.courseQuestionClean("1")}}})]}}])}),s("el-table-column",{attrs:{label:"多选题",width:"140",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[s("a",{staticClass:"dialogTagA"},[t._v(t._s(e.row.countQuestion_B)+"条")]),s("el-button",{attrs:{size:"mini",icon:"el-icon-delete",circle:""},on:{click:function(e){return t.courseQuestionClean("2")}}})]}}])}),s("el-table-column",{attrs:{label:"判断题",width:"140",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[s("a",{staticClass:"dialogTagA"},[t._v(t._s(e.row.countQuestion_C)+"条")]),s("el-button",{attrs:{size:"mini",icon:"el-icon-delete",circle:""},on:{click:function(e){return t.courseQuestionClean("3")}}})]}}])}),s("el-table-column",{attrs:{label:"简答题",width:"140",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[s("a",{staticClass:"dialogTagA"},[t._v(t._s(e.row.countQuestion_D)+"条")]),s("el-button",{attrs:{size:"mini",icon:"el-icon-delete",circle:""},on:{click:function(e){return t.courseQuestionClean("4")}}})]}}])})],1)],1)],1),s("el-row",[s("el-col",{attrs:{span:17,offset:2}},[s("el-upload",{ref:"upload",staticClass:"upload-demo",staticStyle:{height:"100px"},attrs:{headers:t.uploadData,"on-success":t.uploadSuccess,data:t.uploadData,"show-file-list":!0,action:"api_uploadQuestionFile",limit:1,"on-exceed":t.handleExceed,"file-list":t.fileList,"auto-upload":!1}},[s("el-select",{staticClass:"questionFileTypeSelect",attrs:{size:"medium",placeholder:"题型"},model:{value:t.select,callback:function(e){t.select=e},expression:"select"}},[s("el-option",{attrs:{label:"单选题",value:"1"}}),s("el-option",{attrs:{label:"多选题",value:"2"}}),s("el-option",{attrs:{label:"判断题",value:"3"}}),s("el-option",{attrs:{label:"简答题",value:"4"}})],1),s("el-button",{staticStyle:{"margin-left":"20px"},attrs:{slot:"trigger",size:"medium",icon:"el-icon-folder-add"},slot:"trigger"}),s("el-button",{staticStyle:{"margin-left":"20px","margin-top":"30px",float:"right"},attrs:{size:"medium",type:"success"},on:{click:function(e){return t.submitUpload()}}},[t._v("上传")])],1)],1),s("el-col",{staticStyle:{"margin-top":"55px"},attrs:{span:4,align:"left"}},[s("a",{attrs:{href:"/导入题库的样题.rar"}},[t._v("样题下载")])])],1)],1),s("el-dialog",{attrs:{visible:t.addCourseDialog,title:"新增科目",width:"30%"},on:{"update:visible":function(e){t.addCourseDialog=e},close:function(e){return t.addCourseDialogClose()}}},[s("el-divider"),s("el-row",{staticClass:"firstRowInDrawer"},[s("el-col",{staticStyle:{"margin-top":"6px"},attrs:{span:5,offset:2,align:"right"}},[t._v("\n            科目名：\n          ")]),s("el-col",{attrs:{span:10,align:"left"}},[s("el-input",{attrs:{size:"medium",placeholder:"输入科目名"},model:{value:t.newCourseNameInput,callback:function(e){t.newCourseNameInput=e},expression:"newCourseNameInput"}})],1),s("el-col",{attrs:{span:4,align:"right"}},[s("el-button",{attrs:{size:"medium"},on:{click:function(e){return t.submitAddingCourse()}}},[t._v("确定")])],1)],1)],1)],1)},i=[],n=s("bc3a"),a=s.n(n),l=s("4328"),u=s.n(l),r={data:function(){return{courseList:[],currCourseId:"",currCourseName:"",questionEditDialog_Data:[],questionEditDialog:!1,addCourseDialog:!1,input1:"",select:"",fileList:[],newCourseNameInput:""}},computed:{uploadData:function(){return{courseId:this.currCourseId,questionType:this.select}}},created:function(){this.getMsg(),this.getAllCourse()},methods:{getMsg:function(){var t=this;a.a.get("/test").then(function(e){t.msg=e.data,console.log(e.data)})},getAllCourse:function(){var t=this;a.a.get("/api_getAllCourse").then(function(e){t.courseList=e.data})},questionEdit:function(t){var e=this;e.courseId="",e.questionEditDialog_Data=[],e.questionEditDialog_Data.push(t),e.currCourseId=t.id,e.currCourseName=t.courseName,e.questionEditDialog=!0},questionEditDialogClose:function(){var t=this;t.questionEditDialog_Data=[],t.currCourseId=""},courseEdit:function(t){},courseDelete:function(t){this.deleteConfirm(t)},courseQuestionClean:function(t){var e=this;"1"===t&&this.$alert("删除所有的单选题？","请谨慎操作",{confirmButtonText:"继续",callback:function(s){"confirm"===s&&a.a.post("/api_deleteQuestionByType",u.a.stringify({courseId:e.currCourseId,questionType:t})).then(function(t){e.showSuccessMsg(t.data.msgContent),e.questionEditDialog=!1,e.getAllCourse()})}}),"4"===t&&this.$alert("删除所有的简答题？","请谨慎操作",{confirmButtonText:"继续",callback:function(s){"confirm"===s&&a.a.post("/api_deleteQuestionByType",u.a.stringify({courseId:e.currCourseId,questionType:t})).then(function(t){e.showSuccessMsg(t.data.msgContent),e.questionEditDialog=!1,e.getAllCourse()})}}),"2"===t&&this.$alert("删除所有的多选题？","请谨慎操作",{confirmButtonText:"继续",callback:function(s){"confirm"===s&&a.a.post("/api_deleteQuestionByType",u.a.stringify({courseId:e.currCourseId,questionType:t})).then(function(t){e.showSuccessMsg(t.data.msgContent),e.questionEditDialog=!1,e.getAllCourse()})}}),"3"===t&&this.$alert("删除所有的判断题？","请谨慎操作",{confirmButtonText:"继续",callback:function(s){"confirm"===s&&a.a.post("/api_deleteQuestionByType",u.a.stringify({courseId:e.currCourseId,questionType:t})).then(function(t){e.showSuccessMsg(t.data.msgContent),e.questionEditDialog=!1,e.getAllCourse()})}})},deleteConfirm:function(t){var e=this;this.$alert("此操作将删除该科目","请谨慎操作",{confirmButtonText:"继续",callback:function(s){"confirm"===s&&a.a.post("/api_deleteCourse",u.a.stringify({courseId:t.id})).then(function(t){0===t.data.msgType?(e.showSuccessMsg(t.data.msgContent),e.getAllCourse()):1===t.data.msgType&&e.showErrorMsg(t.data.msgContent)})}})},uploadSuccess:function(t,e,s){var o=this;o.questionEditDialog=!1,o.questionEditDialog_Data=[],o.courseId="",o.currCourseName="",o.select="",this.$refs.upload.clearFiles(),this.getAllCourse(),0===t.msgType?this.showSuccessMsg(t.msgContent):1===t.msgType&&this.showErrorMsg(t.msgContent)},submitUpload:function(){""!==this.select?this.$refs.upload.submit():this.showErrorMsg("请选择题型")},handleExceed:function(t,e){},showSuccessMsg:function(t){this.$message({message:t,type:"success"})},showErrorMsg:function(t){this.$message({message:t,type:"error"})},openAddCourseDialog:function(){this.addCourseDialog=!0},addCourseDialogClose:function(){this.newCourseNameInput=""},submitAddingCourse:function(){var t=this;""!==t.newCourseNameInput?a.a.post("/api_newCourse",u.a.stringify({courseName:t.newCourseNameInput})).then(function(e){t.showSuccessMsg(e.data.msgContent),t.addCourseDialog=!1,t.newCourseNameInput="",t.getAllCourse()}):t.showErrorMsg("请填写科目名")}}},c=r,d=(s("c872"),s("2877")),g=Object(d["a"])(c,o,i,!1,null,"224aaf1b",null);e["default"]=g.exports},c872:function(t,e,s){"use strict";var o=s("dcf2"),i=s.n(o);i.a},dcf2:function(t,e,s){}}]);
//# sourceMappingURL=chunk-af5b9a7e.66fa686b.js.map