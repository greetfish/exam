(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0e5ec5"],{"970a":function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"paperForExam"},[a("el-row",[a("el-col",{attrs:{span:22,offset:1}},[a("h3",[e._v("未开考试卷列表")]),a("el-divider")],1)],1),a("el-row",[a("el-col",{attrs:{span:18,offset:3,align:"left"}},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.paperList,stripe:""}},[a("el-table-column",{attrs:{type:"index",width:"50"}}),a("el-table-column",{attrs:{prop:"paperName",label:"试卷名"}}),a("el-table-column",{attrs:{prop:"generateTime",label:"创建时间"}}),a("el-table-column",{attrs:{prop:"course.courseName",label:"科目"}}),a("el-table-column",{attrs:{prop:"memberGroup.groupName",label:"人员组"}}),a("el-table-column",{attrs:{prop:"memberGroup.countMember",label:"人数"}}),a("el-table-column",{attrs:{prop:"",label:"",width:"240",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"mini",round:""},on:{click:function(a){return e.setPaperToExam(t.row)}}},[e._v("开考")]),a("el-button",{attrs:{type:"danger",icon:"el-icon-delete",size:"mini",circle:""},on:{click:function(a){return e.deletepre(t.row)}}})]}}])})],1)],1)],1)],1)},n=[],s=a("bc3a"),o=a.n(s),l=a("4328"),i=a.n(l),p={data:function(){return{paperList:[]}},created:function(){this.getAllPaper()},methods:{getAllPaper:function(){var e=this;o.a.post("/api_getAllPaper",i.a.stringify({paperState:"0"})).then(function(t){e.paperList=t.data})},setPaperToExam:function(e){var t=this;this.$alert("将试卷设置为开始考试，考生可以开始答题","提示",{confirmButtonText:"继续",callback:function(a){"confirm"===a&&o.a.post("/api_setPaperToExam",i.a.stringify({paperId:e.id})).then(function(e){t.getAllPaper(),0===e.data.msgType?t.showSuccessMsg(e.data.msgContent):1===e.data.msgType&&t.showErrorMsg(e.data.msgContent)})}})},deletepre:function(e){this.deletePaper(e)},deletePaper:function(e){var t=this;this.$alert("此操作将删除试卷【"+e.paperName+"】，但试卷暂未开考，可放心删除","请谨慎操作",{confirmButtonText:"继续",callback:function(a){"confirm"===a&&o.a.post("/api_deletePaper",i.a.stringify({paperId:e.id})).then(function(e){t.getAllPaper(),0===e.data.msgType?t.showSuccessMsg(e.data.msgContent):1===e.data.msgType&&t.showErrorMsg(e.data.msgContent)})}})},showSuccessMsg:function(e){this.$message({message:e,type:"success"})},showErrorMsg:function(e){this.$message({message:e,type:"error"})}}},c=p,u=a("2877"),m=Object(u["a"])(c,r,n,!1,null,null,null);t["default"]=m.exports}}]);
//# sourceMappingURL=chunk-2d0e5ec5.9253bc87.js.map