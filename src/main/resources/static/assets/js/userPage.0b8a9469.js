(function(e){function t(t){for(var o,r,s=t[0],l=t[1],c=t[2],u=0,f=[];u<s.length;u++)r=s[u],a[r]&&f.push(a[r][0]),a[r]=0;for(o in l)Object.prototype.hasOwnProperty.call(l,o)&&(e[o]=l[o]);d&&d(t);while(f.length)f.shift()();return i.push.apply(i,c||[]),n()}function n(){for(var e,t=0;t<i.length;t++){for(var n=i[t],o=!0,r=1;r<n.length;r++){var s=n[r];0!==a[s]&&(o=!1)}o&&(i.splice(t--,1),e=l(l.s=n[0]))}return e}var o={},r={userPage:0},a={userPage:0},i=[];function s(e){return l.p+"assets/js/"+({}[e]||e)+"."+{"chunk-1cce52ed":"4995c731","chunk-524ce3e9":"bfd62ef6"}[e]+".js"}function l(t){if(o[t])return o[t].exports;var n=o[t]={i:t,l:!1,exports:{}};return e[t].call(n.exports,n,n.exports,l),n.l=!0,n.exports}l.e=function(e){var t=[],n={"chunk-1cce52ed":1,"chunk-524ce3e9":1};r[e]?t.push(r[e]):0!==r[e]&&n[e]&&t.push(r[e]=new Promise(function(t,n){for(var o="assets/css/"+({}[e]||e)+"."+{"chunk-1cce52ed":"26e5df9c","chunk-524ce3e9":"853fea8d"}[e]+".css",a=l.p+o,i=document.getElementsByTagName("link"),s=0;s<i.length;s++){var c=i[s],u=c.getAttribute("data-href")||c.getAttribute("href");if("stylesheet"===c.rel&&(u===o||u===a))return t()}var f=document.getElementsByTagName("style");for(s=0;s<f.length;s++){c=f[s],u=c.getAttribute("data-href");if(u===o||u===a)return t()}var d=document.createElement("link");d.rel="stylesheet",d.type="text/css",d.onload=t,d.onerror=function(t){var o=t&&t.target&&t.target.src||a,i=new Error("Loading CSS chunk "+e+" failed.\n("+o+")");i.code="CSS_CHUNK_LOAD_FAILED",i.request=o,delete r[e],d.parentNode.removeChild(d),n(i)},d.href=a;var m=document.getElementsByTagName("head")[0];m.appendChild(d)}).then(function(){r[e]=0}));var o=a[e];if(0!==o)if(o)t.push(o[2]);else{var i=new Promise(function(t,n){o=a[e]=[t,n]});t.push(o[2]=i);var c,u=document.createElement("script");u.charset="utf-8",u.timeout=120,l.nc&&u.setAttribute("nonce",l.nc),u.src=s(e),c=function(t){u.onerror=u.onload=null,clearTimeout(f);var n=a[e];if(0!==n){if(n){var o=t&&("load"===t.type?"missing":t.type),r=t&&t.target&&t.target.src,i=new Error("Loading chunk "+e+" failed.\n("+o+": "+r+")");i.type=o,i.request=r,n[1](i)}a[e]=void 0}};var f=setTimeout(function(){c({type:"timeout",target:u})},12e4);u.onerror=u.onload=c,document.head.appendChild(u)}return Promise.all(t)},l.m=e,l.c=o,l.d=function(e,t,n){l.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},l.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},l.t=function(e,t){if(1&t&&(e=l(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var n=Object.create(null);if(l.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var o in e)l.d(n,o,function(t){return e[t]}.bind(null,o));return n},l.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return l.d(t,"a",t),t},l.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},l.p="",l.oe=function(e){throw console.error(e),e};var c=window["webpackJsonp"]=window["webpackJsonp"]||[],u=c.push.bind(c);c.push=t,c=c.slice();for(var f=0;f<c.length;f++)t(c[f]);var d=u;i.push([1,"chunk-vendors"]),n()})({1:function(e,t,n){e.exports=n("377c")},"377c":function(e,t,n){"use strict";n.r(t);n("cadf"),n("551c"),n("f751"),n("097d");var o=n("a026"),r=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"app"}},[n("router-view")],1)},a=[],i=(n("b985"),n("2877")),s={},l=Object(i["a"])(s,r,a,!1,null,null,null),c=l.exports,u=n("8c4f"),f=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"login"},[n("div",{staticClass:"loginDiv"},[n("el-row",[n("el-col",{staticClass:"loginForm",attrs:{span:6,offset:16}},[n("el-row",[n("el-col",{attrs:{span:13,offset:1,align:"left"}},[n("h3",[e._v("考试登录：")])]),n("el-col",{attrs:{span:8,align:"right"}},[n("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"管理员登录",placement:"bottom"}},[n("router-link",{staticClass:"linkToAdmin topIcon",attrs:{to:"/admin"}},[n("i",{staticClass:"linkToAdmin el-icon-s-tools"})])],1)],1)],1),n("el-row",[n("el-col",{attrs:{span:22,offset:1,align:"left"}},[n("el-input",{staticClass:"loginInput",attrs:{size:"medium",placeholder:"请输入考号"},model:{value:e.examNumber,callback:function(t){e.examNumber=t},expression:"examNumber"}})],1)],1),n("el-row",{staticClass:"loginRow"},[n("el-col",{attrs:{span:8,offset:1,align:"left"}},[n("el-input",{staticClass:"loginInput",attrs:{size:"medium",placeholder:"输入验证码"},model:{value:e.sureCode,callback:function(t){e.sureCode=t},expression:"sureCode"}})],1),n("el-col",{attrs:{span:2,offset:3,align:"left"}},[n("span",{staticClass:"changeCode"},[n("i",{staticClass:"el-icon-refresh",on:{click:function(t){return e.changeCode()}}})])]),n("el-col",{staticClass:"code",attrs:{span:9,align:"center"}},[n("span",{attrs:{oncopy:"event.returnValue=false",onselectstart:"event.returnValue=false",ondragstart:"event.returnValue=false",oncontextmenu:"event.returnValue=false"}},[e._v(e._s(e.code))])])],1),n("el-row",{staticClass:"loginRow"},[n("el-col",{staticStyle:{"padding-bottom":"20px"},attrs:{span:22,offset:1,align:"right"}},[n("el-button",{on:{click:function(t){return e.login()}}},[e._v("登录")])],1)],1)],1)],1),n("el-row",{staticClass:"infoRow"},[n("el-col",{attrs:{span:6,offset:16}},[n("el-collapse",{staticStyle:{"baground-color":"transparent"}},[n("el-collapse-item",{staticClass:"collapseItem",staticStyle:{"baground-color":"transparent"},attrs:{title:"关于",name:"3"}},[n("el-row",{staticClass:"rowInItem"},[n("el-col",{attrs:{span:8,offset:1,align:"right"}},[e._v("\n                开发者：\n              ")]),n("el-col",{attrs:{span:12,align:"left"}},[e._v("\n                96721部队____杨万\n              ")])],1),n("el-row",{staticClass:"rowInItem"},[n("el-col",{attrs:{span:8,offset:1,align:"right"}},[e._v("\n                联系方式：\n              ")]),n("el-col",{attrs:{span:12,align:"left"}},[e._v("\n                greetfish@foxmail.com\n              ")])],1)],1)],1)],1)],1)],1)])},d=[],m=(n("7f7f"),n("bc3a")),p=n.n(m),h=n("4328"),g=n.n(h),b={data:function(){return{sureCode:"",examNumber:"",codeChars:[0,2,3,4,5,6,7,8,9,"a","b","c","d","e","f","g","h","j","k","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","J","K","L","M","N","R","T","U","V","W","X","Y","Z"],codeLength:4,code:"",memberPaperList:[],memberName:""}},created:function(){this.changeCode()},methods:{login:function(){if(this.sureCode===this.code){var e=this;p.a.post("/user_examLogin",g.a.stringify({examNumber:e.examNumber,sureCode:e.sureCode})).then(function(t){0===t.data.length?(e.showErrorMsg("暂时没有有关该考号的待考核试卷"),e.loginFail(e)):(e.memberPaperList=t.data,e.memberName=t.data[0].member.name,e.loginSuccess(e))})}else this.showErrorMsg("验证码不正确，请重新输入"),this.sureCode="",this.changeCode()},changeCode:function(){var e=this;e.code="";for(var t=0;t<e.codeLength;t++){var n=Math.floor(54*Math.random());e.code+=e.codeChars[n]}},loginFail:function(e){e.changeCode(),e.sureCode="",e.examNumber=""},loginSuccess:function(e){e.$router.push({path:"/examList",query:{memberPaperList:e.memberPaperList,memberName:e.memberName}})},showErrorMsg:function(e){this.$message({message:e,type:"error"})}}},v=b,w=(n("b8c1"),Object(i["a"])(v,f,d,!1,null,null,null)),C=w.exports;o["default"].use(u["a"]);var y=new u["a"]({mode:"history",base:"",routes:[{path:"/",name:"login",component:C},{path:"/examList",name:"examList",component:function(){return n.e("chunk-1cce52ed").then(n.bind(null,"77d8"))}},{path:"/examing",name:"examing",component:function(){return n.e("chunk-524ce3e9").then(n.bind(null,"6897"))}},{path:"/admin",beforeEnter:function(e,t,n){window.location="/admin.html"}},{path:"/home",beforeEnter:function(e,t,n){window.location="/admin.html"}},{path:"/courseManage",beforeEnter:function(e,t,n){window.location="/admin.html"}},{path:"/examingControl",beforeEnter:function(e,t,n){window.location="/admin.html"}},{path:"/paperArchive",beforeEnter:function(e,t,n){window.location="/admin.html"}},{path:"/memberArchive",beforeEnter:function(e,t,n){window.location="/admin.html"}},{path:"/memberReview",beforeEnter:function(e,t,n){window.location="/admin.html"}},{path:"/paperForExam",beforeEnter:function(e,t,n){window.location="/admin.html"}},{path:"/paperGenerate",beforeEnter:function(e,t,n){window.location="/admin.html"}},{path:"/scoreExport",beforeEnter:function(e,t,n){window.location="/admin.html"}},{path:"/controlExamMember",beforeEnter:function(e,t,n){window.location="/admin.html"}}]}),x=n("c0d6"),E=n("5c96"),_=n.n(E);n("0fae");o["default"].config.productionTip=!1,o["default"].use(_.a),new o["default"]({router:y,store:x["a"],render:function(e){return e(c)}}).$mount("#app")},a637:function(e,t,n){},b8c1:function(e,t,n){"use strict";var o=n("d6f0"),r=n.n(o);r.a},b985:function(e,t,n){"use strict";var o=n("a637"),r=n.n(o);r.a},c0d6:function(e,t,n){"use strict";var o=n("a026"),r=n("2f62");o["default"].use(r["a"]),t["a"]=new r["a"].Store({state:{},mutations:{},actions:{}})},d6f0:function(e,t,n){}});
//# sourceMappingURL=userPage.0b8a9469.js.map