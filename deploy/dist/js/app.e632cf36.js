(function(t){function e(e){for(var n,i,o=e[0],c=e[1],l=e[2],d=0,m=[];d<o.length;d++)i=o[d],Object.prototype.hasOwnProperty.call(s,i)&&s[i]&&m.push(s[i][0]),s[i]=0;for(n in c)Object.prototype.hasOwnProperty.call(c,n)&&(t[n]=c[n]);u&&u(e);while(m.length)m.shift()();return r.push.apply(r,l||[]),a()}function a(){for(var t,e=0;e<r.length;e++){for(var a=r[e],n=!0,o=1;o<a.length;o++){var c=a[o];0!==s[c]&&(n=!1)}n&&(r.splice(e--,1),t=i(i.s=a[0]))}return t}var n={},s={app:0},r=[];function i(e){if(n[e])return n[e].exports;var a=n[e]={i:e,l:!1,exports:{}};return t[e].call(a.exports,a,a.exports,i),a.l=!0,a.exports}i.m=t,i.c=n,i.d=function(t,e,a){i.o(t,e)||Object.defineProperty(t,e,{enumerable:!0,get:a})},i.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})},i.t=function(t,e){if(1&e&&(t=i(t)),8&e)return t;if(4&e&&"object"===typeof t&&t&&t.__esModule)return t;var a=Object.create(null);if(i.r(a),Object.defineProperty(a,"default",{enumerable:!0,value:t}),2&e&&"string"!=typeof t)for(var n in t)i.d(a,n,function(e){return t[e]}.bind(null,n));return a},i.n=function(t){var e=t&&t.__esModule?function(){return t["default"]}:function(){return t};return i.d(e,"a",e),e},i.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},i.p="";var o=window["webpackJsonp"]=window["webpackJsonp"]||[],c=o.push.bind(o);o.push=e,o=o.slice();for(var l=0;l<o.length;l++)e(o[l]);var u=c;r.push([0,"chunk-vendors"]),a()})({0:function(t,e,a){t.exports=a("56d7")},"08fb":function(t,e,a){"use strict";var n=a("b6e9"),s=a.n(n);s.a},"12f2":function(t,e,a){"use strict";var n=a("bb88"),s=a.n(n);s.a},"34b5":function(t,e,a){"use strict";var n=a("65f2"),s=a.n(n);s.a},"4a3a":function(t,e,a){"use strict";var n=a("b9ae"),s=a.n(n);s.a},"56d7":function(t,e,a){"use strict";a.r(e);a("cadf"),a("551c"),a("f751"),a("097d");var n=a("2b0e"),s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"app"}},[a("router-view")],1)},r=[],i=(a("7c55"),a("2877")),o={},c=Object(i["a"])(o,s,r,!1,null,null,null),l=c.exports,u=a("8c4f"),d=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"container"},[a("div",{staticClass:"content"},[a("div",{staticClass:"login-container animated fadeInDown",staticStyle:{"animation-delay":".3s"}},[a("div",{staticClass:"login justify-content-center",attrs:{id:"login-form"}},[t._m(0),a("div",{staticClass:"form-container animated fadeIn",staticStyle:{"animation-delay":".7s"}},[a("form",{attrs:{method:"POST"}},[t._m(1),a("input",{directives:[{name:"model",rawName:"v-model",value:t.user.userName,expression:"user.userName"}],attrs:{name:"userName"},domProps:{value:t.user.userName},on:{input:function(e){e.target.composing||t.$set(t.user,"userName",e.target.value)}}}),t._m(2),a("input",{directives:[{name:"model",rawName:"v-model",value:t.user.password,expression:"user.password"}],attrs:{type:"password",name:"password",placeholder:"Password"},domProps:{value:t.user.password},on:{input:function(e){e.target.composing||t.$set(t.user,"password",e.target.value)}}}),a("div",{staticClass:"submit-buttons"},[a("input",{attrs:{type:"button",value:"Connect"},on:{click:t.login}}),a("input",{staticClass:"btn-register",attrs:{type:"button",value:"Sign Up"},on:{click:function(e){return t.$router.push("/register")}}})])])])]),a("div",{staticClass:"login animated fadeIn",staticStyle:{"animation-delay":".7s","animation-duration":"4s"},attrs:{id:"login-bg"}})])])])},m=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("h1",{staticClass:"form-title"},[a("i",{staticClass:"fas fa-user",staticStyle:{color:"#55a0ff"}}),a("br"),t._v("LOGIN\n\t\t\t\t\t"),a("hr")])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("label",{attrs:{for:""}},[a("i",{staticClass:"fas fa-at"}),t._v(" UserName\n\t\t\t\t\t\t")])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("label",{attrs:{for:""}},[a("i",{staticClass:"fab fa-slack-hash"}),t._v(" Password\n\t\t\t\t\t\t")])}],f={data:function(){return{user:{}}},methods:{login:function(){var t=this;this.$http({url:"/user/login",method:"post",data:this.user}).then((function(e){sessionStorage.setItem("userName",e.userName),sessionStorage.setItem("userId",e.id),sessionStorage.setItem("userInfo",JSON.stringify(e)),t.$router.push("/page/home")}))}}},p=f,h=(a("4a3a"),Object(i["a"])(p,d,m,!1,null,"443eb31e",null)),g=h.exports,v=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"container"},[a("div",{staticClass:"content"},[a("div",{staticClass:"login-container animated fadeInDown",staticStyle:{"animation-delay":".3s"}},[a("div",{staticClass:"login justify-content-center",attrs:{id:"login-form"}},[t._m(0),a("div",{staticClass:"form-container animated fadeIn",staticStyle:{"animation-delay":".7s"}},[a("form",{staticStyle:{height:"60vh",overflow:"auto"},attrs:{method:"POST"}},[t._m(1),a("input",{directives:[{name:"model",rawName:"v-model",value:t.info.userName,expression:"info.userName"}],attrs:{type:"text",name:"name",placeholder:"Name",required:"",autofocus:""},domProps:{value:t.info.userName},on:{input:function(e){e.target.composing||t.$set(t.info,"userName",e.target.value)}}}),t._m(2),a("input",{directives:[{name:"model",rawName:"v-model",value:t.info.email,expression:"info.email"}],attrs:{type:"email",name:"email",placeholder:"E-mail"},domProps:{value:t.info.email},on:{input:function(e){e.target.composing||t.$set(t.info,"email",e.target.value)}}}),t._m(3),a("input",{directives:[{name:"model",rawName:"v-model",value:t.info.password,expression:"info.password"}],attrs:{type:"password",name:"password",placeholder:"Password"},domProps:{value:t.info.password},on:{input:function(e){e.target.composing||t.$set(t.info,"password",e.target.value)}}}),t._m(4),a("input",{attrs:{type:"password",name:"password_confirmation",placeholder:"Password"}}),t._m(5),a("input",{directives:[{name:"model",rawName:"v-model",value:t.info.age,expression:"info.age"}],attrs:{type:"text",placeholder:"Age",required:"",autofocus:""},domProps:{value:t.info.age},on:{input:function(e){e.target.composing||t.$set(t.info,"age",e.target.value)}}}),t._m(6),a("br"),a("br"),a("div",{staticStyle:{display:"flex"}},[a("span",{staticStyle:{width:"50px"}},[t._v("Male")]),a("input",{directives:[{name:"model",rawName:"v-model",value:t.info.gender,expression:"info.gender"}],staticStyle:{width:"150px"},attrs:{type:"radio",name:"gender"},domProps:{value:0,checked:t._q(t.info.gender,0)},on:{change:function(e){return t.$set(t.info,"gender",0)}}})]),a("div",{staticStyle:{display:"flex"}},[a("span",{staticStyle:{width:"50px"}},[t._v("Female")]),a("input",{directives:[{name:"model",rawName:"v-model",value:t.info.gender,expression:"info.gender"}],staticStyle:{width:"150px"},attrs:{type:"radio",name:"gender"},domProps:{value:1,checked:t._q(t.info.gender,1)},on:{change:function(e){return t.$set(t.info,"gender",1)}}})]),t._m(7),a("input",{directives:[{name:"model",rawName:"v-model",value:t.info.city,expression:"info.city"}],attrs:{type:"text",placeholder:"City",required:"",autofocus:""},domProps:{value:t.info.city},on:{input:function(e){e.target.composing||t.$set(t.info,"city",e.target.value)}}}),a("div",{staticClass:"submit-buttons"},[a("input",{staticStyle:{background:"#55efc4"},attrs:{type:"submit",value:"Submit"},on:{click:t.register}})])])])]),a("div",{staticClass:"login animated fadeIn",staticStyle:{"animation-delay":".7s","animation-duration":"4s"},attrs:{id:"login-bg"}})])])])},y=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("h1",{staticClass:"form-title"},[a("i",{staticClass:"fas fa-user",staticStyle:{color:"#55a0ff"}}),a("br"),t._v("Sign Up\n\t\t\t\t\t"),a("hr")])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("label",{attrs:{for:""}},[a("i",{staticClass:"fab fa-amilia"}),t._v(" Name\n\t\t\t\t\t\t")])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("label",{attrs:{for:""}},[a("i",{staticClass:"fas fa-at"}),t._v(" Email\n\t\t\t\t\t\t")])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("label",{attrs:{for:""}},[a("i",{staticClass:"fab fa-slack-hash"}),t._v(" Password\n\t\t\t\t\t\t")])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("label",{attrs:{for:""}},[a("i",{staticClass:"fab fa-slack-hash"}),t._v(" Confirm Password\n\t\t\t\t\t\t")])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("label",{attrs:{for:""}},[a("i",{staticClass:"fab fa-amilia"}),t._v(" Age\n\t\t\t\t\t\t")])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("label",{attrs:{for:""}},[a("i",{staticClass:"fab fa-amilia"}),t._v(" Gender\n\t\t\t\t\t\t")])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("label",{attrs:{for:""}},[a("i",{staticClass:"fab fa-amilia"}),t._v(" City\n\t\t\t\t\t\t")])}],b={name:"home",components:{},data:function(){return{info:{}}},created:function(){},methods:{register:function(){var t=this;this.$http({url:"/user/register",method:"post",data:this.info}).then((function(e){t.$Notify({title:"Register Ok",type:"success"})}))}}},w=b,_=(a("fcbd"),Object(i["a"])(w,v,y,!1,null,"c643e7ca",null)),I=_.exports,C=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[t.tableData.length?a("at-alert",{attrs:{message:"New Friends",description:"You have "+t.tableData.length+" friend requests",type:"info","show-icon":"",closable:""},nativeOn:{click:function(e){t.showFriends=!0}}}):t._e(),a("div",{staticClass:"wrapper"},[a("List",{staticStyle:{"margin-right":"100px"},attrs:{data:t.list},on:{"item-click":t.handleClick}}),a("Trend",{attrs:{data:t.trend},on:{go:t.go}})],1),a("at-modal",{attrs:{title:"New Friends"},model:{value:t.showFriends,callback:function(e){t.showFriends=e},expression:"showFriends"}},[a("at-table",{attrs:{columns:t.cols,data:t.tableData,border:!1}})],1)],1)},S=[],P=(a("6c7b"),function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("ul",t._l(t.data,(function(e,n){return a("li",{key:n,on:{click:function(a){return a.stopPropagation(),t.$emit("item-click",e)}}},[a("img",{attrs:{src:e.img||"https://misc.aotu.io/koppthe/at-ui/cover.jpg",alt:""}}),a("a",[t._v(t._s(e.name))])])})),0)}),$=[],O={props:{data:{type:Array,default:function(){return{}}}}},k=O,x=(a("12f2"),Object(i["a"])(k,P,$,!1,null,"71a56121",null)),N=x.exports,j=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"wrapper-trend"},[a("h1",[t._v("Trend")]),a("ul",t._l(t.data,(function(e,n){return a("li",{key:n,staticStyle:{display:"flex","justify-content":"space-between"}},[a("span",[t._v("\n\t\t\t\t"+t._s(n+1)+"    \n\t\t\t\t"),a("a",{on:{click:function(a){return t.go(e.id)}}},[t._v(t._s(e.name))])]),a("at-rate",{attrs:{value:e.score,disabled:""}})],1)})),0)])},R=[],A={props:{data:{type:Array,default:function(){return{}}}},methods:{go:function(t){console.log(t),this.$emit("go",t)}}},T=A,D=(a("c44e"),Object(i["a"])(T,j,R,!1,null,"6217e15e",null)),E=D.exports,G={components:{List:N,Trend:E},data:function(){var t=this;return{list:new Array(1).fill({img:"https://misc.aotu.io/koppthe/at-ui/cover.jpg",name:"TOM"}),trend:new Array(1).fill({name:"TOM"}),pageSize:999,pageNo:1,totalPage:1,showFriends:!1,tableData:[],cols:[{title:"userName",key:"userName"},{title:"requestTime",key:"requestTime"},{title:"Action",render:function(e,a){return e("AtButton",{props:{size:"smaller",type:"info",hollow:!0},on:{click:function(){t.$http({url:"/friendship/acceptFriendship",method:"post",data:{friendId:a.item.id,userId:sessionStorage.getItem("userId")}}).then((function(e){t.$Notify({title:"Success",type:"success"}),t.getFrendRequest()}))}}},"Accept")}}]}},created:function(){this.getAllGames(),this.getFrendRequest(),this.getGameTrend()},methods:{handleClick:function(t){this.$router.push("/page/game?id="+t.id)},getFrendRequest:function(){var t=this;this.$http({url:"/friendship/getFriendRequests",method:"get",data:{pageSize:this.pageSize,pageNo:this.pageNo,userId:sessionStorage.getItem("userId")}}).then((function(e){e.data&&(t.tableData=e.data)}))},getAllGames:function(){var t=this;this.$http({url:"/game/viewAll",method:"get",data:{pageSize:this.pageSize,pageNo:this.pageNo}}).then((function(e){t.list=e.data.map((function(t){return{name:t.gameName,id:t.id}})),t.totalPage=e.totalPage}))},go:function(t){this.$router.push("/page/game?id="+t)},getGameTrend:function(){var t=this;this.$http({url:"/game/sortScore",method:"get",data:{pageSize:10,pageNo:1}}).then((function(e){e.data&&(t.trend=e.data.map((function(t){return{name:t.gameName,score:t.averageScore,id:t._id}})))}))}}},F=G,M=(a("c08b"),Object(i["a"])(F,C,S,!1,null,"d84910ee",null)),q=M.exports,z=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"wrapper"},[a("div",{staticClass:"form"},[a("div",{staticClass:"form-item"},[a("div",{staticClass:"label"},[t._v("userName")]),a("at-input",{model:{value:t.userInfo.userName,callback:function(e){t.$set(t.userInfo,"userName",e)},expression:"userInfo.userName"}})],1),a("div",{staticClass:"form-item"},[a("div",{staticClass:"label"},[t._v("email")]),a("at-input",{model:{value:t.userInfo.email,callback:function(e){t.$set(t.userInfo,"email",e)},expression:"userInfo.email"}})],1),a("div",{staticClass:"form-item"},[a("div",{staticClass:"label"},[t._v("city")]),a("at-input",{model:{value:t.userInfo.city,callback:function(e){t.$set(t.userInfo,"city",e)},expression:"userInfo.city"}})],1),a("div",{staticClass:"form-item"},[a("div",{staticClass:"label"},[t._v("gender")]),a("at-radio",{attrs:{label:0},model:{value:t.userInfo.gender,callback:function(e){t.$set(t.userInfo,"gender",e)},expression:"userInfo.gender"}},[t._v("Male")]),a("at-radio",{attrs:{label:1},model:{value:t.userInfo.gender,callback:function(e){t.$set(t.userInfo,"gender",e)},expression:"userInfo.gender"}},[t._v("Female")])],1),a("div",{staticClass:"form-item"},[a("div",{staticClass:"label"},[t._v("age")]),a("at-input-number",{attrs:{min:0,max:100},model:{value:t.userInfo.age,callback:function(e){t.$set(t.userInfo,"age",e)},expression:"userInfo.age"}})],1),a("br"),a("at-button",{attrs:{type:"primary"},on:{click:t.submit}},[t._v("Submit")]),t._v("     \n\t\t"),a("at-button",{attrs:{type:"primary"},on:{click:function(e){t.showModal=!0}}},[t._v("Reset Password")]),a("br"),a("br"),a("h2",[t._v("My Games")]),a("at-table",{attrs:{columns:t.gameCols,data:t.gameList,border:!1}}),a("br"),a("br"),a("h2",[t._v("My Friends")]),a("at-table",{attrs:{columns:t.friendCols,data:t.friendList,border:!1}}),a("at-modal",{attrs:{title:"Reset Password"},on:{"on-confirm":t.handleConfirm,"on-cancel":t.handleCancel},model:{value:t.showModal,callback:function(e){t.showModal=e},expression:"showModal"}},[a("div",{staticClass:"form"},[a("div",{staticClass:"form-item"},[a("div",{staticClass:"label"},[t._v("Old Password")]),a("at-input",{attrs:{type:"password"},model:{value:t.resetInfo.oldPwd,callback:function(e){t.$set(t.resetInfo,"oldPwd",e)},expression:"resetInfo.oldPwd"}})],1),a("div",{staticClass:"form-item"},[a("div",{staticClass:"label"},[t._v("New Password")]),a("at-input",{attrs:{type:"password"},model:{value:t.resetInfo.newPwd,callback:function(e){t.$set(t.resetInfo,"newPwd",e)},expression:"resetInfo.newPwd"}})],1)])])],1)])},L=[],U=(a("8e6e"),a("ac6a"),a("456d"),a("bd86"));function B(t,e){var a=Object.keys(t);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(t);e&&(n=n.filter((function(e){return Object.getOwnPropertyDescriptor(t,e).enumerable}))),a.push.apply(a,n)}return a}function J(t){for(var e=1;e<arguments.length;e++){var a=null!=arguments[e]?arguments[e]:{};e%2?B(Object(a),!0).forEach((function(e){Object(U["a"])(t,e,a[e])})):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(a)):B(Object(a)).forEach((function(e){Object.defineProperty(t,e,Object.getOwnPropertyDescriptor(a,e))}))}return t}var Y={created:function(){this.userInfo=JSON.parse(sessionStorage.getItem("userInfo"))||{},this.getMyGames(),this.getMyFriends()},data:function(){var t=this;return{userInfo:{},resetInfo:{},showModal:!1,gameCols:[{title:"Name",key:"gameName"},{title:"Action",render:function(e,a){return e("AtButton",{props:{size:"smaller",type:"info",hollow:!0},on:{click:function(){t.$http({url:"/game/delToUser",method:"Delete",data:{gameId:a.item.id,userId:sessionStorage.getItem("userId")}}).then((function(e){t.$Notify({title:"Success",type:"success"}),t.getMyGames()}))}}},"Delete")}}],friendCols:[{title:"userName",key:"userName"},{title:"Action",render:function(e,a){return e("AtButton",{props:{size:"smaller",type:"info",hollow:!0},on:{click:function(){t.$http({url:"/friendship/delete",method:"delete",data:{friendId:a.item.id,userId:sessionStorage.getItem("userId")}}).then((function(e){t.$Notify({title:"Success",type:"success"}),t.getMyFriends()}))}}},"Delete")}}],gameList:[],friendList:[]}},methods:{handleConfirm:function(){var t=this;this.$http({url:"/user/resetPwd",method:"put",data:J({},this.resetInfo,{uid:sessionStorage.getItem("userId")})}).then((function(e){t.$Modal.alert({title:"Success",content:"Please login again",callback:function(t){this.$router.push("/")}})}))},handleCancel:function(){},getMyGames:function(){var t=this;this.$http({url:"/game/viewUserGame",method:"get",data:{userId:sessionStorage.getItem("userId"),pageSize:9999,pageNo:1}}).then((function(e){t.gameList=e.data||[]}))},submit:function(){var t=this;this.$http({url:"/user/updateUserInfo",method:"put",data:J({},this.userInfo,{uid:sessionStorage.getItem("userId")})}).then((function(e){t.$Notify({title:"Success",type:"success"}),sessionStorage.setItem("userName",t.userInfo.userName),sessionStorage.setItem("userInfo",JSON.stringify(t.userInfo))}))},getMyFriends:function(){var t=this;this.$http({url:"/friendship/myFriends",method:"get",data:{userId:sessionStorage.getItem("userId"),pageSize:9999,pageNo:1}}).then((function(e){t.friendList=e.data||[]}))}}},W=Y,H=(a("a5b1"),Object(i["a"])(W,z,L,!1,null,"3e69fbf2",null)),K=H.exports,Q=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"wrapper"},[a("List",{staticStyle:{"margin-right":"100px"},attrs:{data:t.list}}),a("Trend",{attrs:{data:t.trend}})],1)},V=[],X={components:{List:N,Trend:E},data:function(){return{list:new Array(8).fill({img:"https://misc.aotu.io/koppthe/at-ui/cover.jpg",name:"TOM"}),trend:new Array(8).fill({name:"TOM"})}}},Z=X,tt=(a("34b5"),Object(i["a"])(Z,Q,V,!1,null,"27082b66",null)),et=tt.exports,at=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"wrapper"},[a("div",{staticClass:"card"},[a("h1",[t._v(t._s(t.game.gameName))]),a("br"),a("h4",[t._v(t._s(t.game.description))])]),a("div",{staticClass:"tab"},[t._m(0),a("at-button",{attrs:{type:"warning",size:"small"},on:{click:function(e){t.showDialog=!0}}},[t._v("+ speak")])],1),a("at-table",{attrs:{columns:t.cols,data:t.tableData,border:!1}}),a("at-modal",{attrs:{title:"Create a post"},on:{"on-confirm":t.handleConfirm,"on-cancel":t.handleCancel},model:{value:t.showDialog,callback:function(e){t.showDialog=e},expression:"showDialog"}},[a("div",{staticClass:"form"},[a("div",{staticClass:"form-item"},[a("div",{staticClass:"label"},[t._v("title")]),a("at-input",{model:{value:t.newPost.title,callback:function(e){t.$set(t.newPost,"title",e)},expression:"newPost.title"}})],1),a("div",{staticClass:"form-item"},[a("div",{staticClass:"label"},[t._v("content")]),a("at-textarea",{attrs:{"min-rows":4},model:{value:t.newPost.content,callback:function(e){t.$set(t.newPost,"content",e)},expression:"newPost.content"}})],1)])]),a("at-modal",{attrs:{title:"Replies",width:"1200"},model:{value:t.showPost,callback:function(e){t.showPost=e},expression:"showPost"}},[a("div",{staticClass:"form",staticStyle:{width:"95%"}},[a("div",{staticClass:"form-item"},[a("div",{staticClass:"label"},[t._v("title")]),a("at-input",{attrs:{readonly:""},model:{value:t.currPost.title,callback:function(e){t.$set(t.currPost,"title",e)},expression:"currPost.title"}})],1),a("div",{staticClass:"form-item"},[a("div",{staticClass:"label"},[t._v("content")]),a("at-textarea",{attrs:{"min-rows":4,readonly:""},model:{value:t.currPost.content,callback:function(e){t.$set(t.currPost,"content",e)},expression:"currPost.content"}})],1),a("div",{staticClass:"form-item"},[a("div",{staticClass:"label"},[t._v("replies")]),a("at-table",{attrs:{columns:t.replyCol,data:t.replyList,border:!1}})],1),a("br"),t.showAddReplyBtn?a("at-button",{attrs:{type:"primary"},on:{click:function(e){t.showAddReply=!0}}},[t._v("Add a Reply")]):t._e(),a("br"),a("br"),t.showAddReply?a("div",{staticClass:"form-item"},[a("at-textarea",{attrs:{"min-rows":4},model:{value:t.replyContent,callback:function(e){t.replyContent=e},expression:"replyContent"}}),a("br"),a("at-button",{on:{click:function(e){return t.addReply("")}}},[t._v("Submit")])],1):t._e()],1)])],1)},nt=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("a",[t._v("Recent discussions")]),t._v("   /  \n\t\t\t"),a("a",[t._v("Hottest discussions")])])}];function st(t,e){var a=Object.keys(t);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(t);e&&(n=n.filter((function(e){return Object.getOwnPropertyDescriptor(t,e).enumerable}))),a.push.apply(a,n)}return a}function rt(t){for(var e=1;e<arguments.length;e++){var a=null!=arguments[e]?arguments[e]:{};e%2?st(Object(a),!0).forEach((function(e){Object(U["a"])(t,e,a[e])})):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(a)):st(Object(a)).forEach((function(e){Object.defineProperty(t,e,Object.getOwnPropertyDescriptor(a,e))}))}return t}var it={data:function(){var t=this;return Object(U["a"])({id:this.$route.query.id,game:{name:"xxx",desc:"xxxx"},cols:[{title:"Discuss",key:"discuss",render:function(e,a){return e("AtButton",{props:{size:"small",type:"text"},on:{click:function(){t.currPost=a.item.fullData,t.getReply(a.item.id),t.showPost=!0}}},a.item.discuss)}},{title:"Author",render:function(t,e){return t("User",{props:{userInfo:e.item.author}})}},{title:"Respond",key:"numReply"},{title:"FinalResponse",key:"finalResponse"},{title:"Detail",render:function(e,a){return e("AtButton",{props:{size:"smaller",type:"info",hollow:!0},on:{click:function(){t.currPost=a.item.fullData,t.getReply(a.item.id),t.showPost=!0}}},"Replies")}}],showPost:!1,tableData:[],showDialog:!1,newPost:{},forumInfo:{},currPost:{},replyContent:"",showAddReply:!1,replyCol:[{title:"Author",render:function(t,e){return t("User",{props:{userInfo:e.item.author}})}},{title:"Content",key:"content"},{title:"Time",key:"replyTime"},{title:"TargetReply",key:"targetReplyContent"},{title:"Action",key:"discuss",render:function(e,a){return e("AtButton",{props:{size:"small"},on:{click:function(){t.showAddReplyBtn=!1,t.showAddReply=!0,t.currTargetReply=a.item.id}}},"Reply")}}],replyList:[],currTargetReply:"",showAddReplyBtn:!0},"currTargetReply","")},created:function(){var t=this;this.getGameInfo(),this.getForumInfo().then((function(){t.getPosts()}))},methods:{getGameInfo:function(){var t=this;this.$http({url:"/game/view",method:"get",data:{gameId:this.id}}).then((function(e){t.game=e}))},getReply:function(t){var e=this;this.$http({url:"/forum/getReplies",method:"get",data:{postId:t,pageNo:1,pageSize:99999}}).then((function(t){e.replyList=t.data.map((function(t){return rt({targetReplyContent:t.targetReply&&t.targetReply.content},t,{replyTime:new Date(t.replyTime).toLocaleString()})}))}))},getForumInfo:function(){var t=this;return this.$http({url:"/forum/getForumInfo",method:"get",data:{gameId:this.id}}).then((function(e){t.forumInfo=e}))},getPosts:function(){var t=this;this.$http({url:"/forum/getPosts",method:"get",data:{forumId:this.forumInfo.id,pageNo:1,pageSize:99999}}).then((function(e){t.tableData=e.data.map((function(t){var e=t.content;return rt({},t,{discuss:t.title,finalResponse:new Date(t.finalReplyTime).toLocaleString(),content:e,numReply:t.numReply,id:t.id})}))}))},handleConfirm:function(){var t=this;this.$http({url:"/forum/createPost",method:"post",data:rt({},this.newPost,{forumId:this.forumInfo.id,authorId:sessionStorage.getItem("userId")})}).then((function(e){t.getForumInfo(),t.getPosts()}))},handleCancel:function(){},addReply:function(){var t=this,e={postId:this.currPost.id,content:this.replyContent,authorId:sessionStorage.getItem("userId")};this.currTargetReply&&(e.targetReplyId=this.currTargetReply),this.$http({url:"/forum/addReply",method:"post",data:e}).then((function(e){t.showAddReply=!1,t.currTargetReply="",t.showAddReplyBtn=!0,t.getReply(t.currPost.id)}))}}},ot=it,ct=(a("08fb"),Object(i["a"])(ot,at,nt,!1,null,"383b649c",null)),lt=ct.exports,ut=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("header",[a("div",{staticClass:"top"},[a("div",[a("router-link",{attrs:{to:"home"}},[t._v("Main Page")])],1),a("div",[a("router-link",{attrs:{to:"my"}},[t._v("My Account")]),a("router-link",{attrs:{to:"/"}},[t._v("Logout")])],1)]),a("div",{staticClass:"bottom"},[a("at-input",{staticStyle:{width:"50vw"},attrs:{size:"large","append-button":""},model:{value:t.query,callback:function(e){t.query=e},expression:"query"}},[a("template",{slot:"append"},[a("i",{staticClass:"icon icon-search",on:{click:t.searchGame}})])],2)],1)]),a("router-view",{key:t.$route.fullPath})],1)},dt=[],mt={data:function(){return{query:""}},methods:{searchGame:function(){var t=this;this.query&&this.$http({url:"/game/search",method:"get",data:{gameName:this.query,pageSize:9999999,pageNo:1}}).then((function(e){if(e.data&&e.data.length){var a=e.data[0];t.query="",t.$router.push("/page/game?id="+a.id)}else alert("No Results")}))}}},ft=mt,pt=(a("88a2"),Object(i["a"])(ft,ut,dt,!1,null,"5af863c3",null)),ht=pt.exports,gt=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("div",{staticClass:"info"},[a("div",{staticClass:"title"},[a("h2",[t._v(t._s(t.game.gameName))]),t.isCollected?a("at-button",{attrs:{type:"warning",icon:"icon-trash-2"},on:{click:t.del}},[t._v("delete")]):a("at-button",{attrs:{type:"primary",icon:"icon-plus"},on:{click:t.collect}},[t._v("collect")]),a("at-button",{staticStyle:{"margin-left":"100px"},attrs:{type:"text"},on:{click:t.goForum}},[t._v("Go to Forums of "+t._s(t.game.gameName)+" >>")])],1),a("div",{staticClass:"base"},[a("img",{attrs:{src:t.game.img||"https://misc.aotu.io/koppthe/at-ui/cover.jpg",alt:""}}),a("div",[a("div",[a("span",[t._v("artist")]),t._v("\n\t\t\t\t\t"+t._s(t.game.artist)+"\n\t\t\t\t")]),a("div",[a("span",[t._v("designer")]),t._v("\n\t\t\t\t\t"+t._s(t.game.designer)+"\n\t\t\t\t")]),a("div",[a("span",[t._v("publisher")]),t._v("\n\t\t\t\t\t"+t._s(t.game.publisher)+"\n\t\t\t\t")]),a("div",[a("span",[t._v("timePerRound")]),t._v("\n\t\t\t\t\t"+t._s(t.game.timePerRound)+"\n\t\t\t\t")]),a("div",[a("span",[t._v("year")]),t._v("\n\t\t\t\t\t"+t._s(t.game.year)+"\n\t\t\t\t")]),a("div",[a("span",[t._v("playerAge")]),t._v("\n\t\t\t\t\t"+t._s(t.game.playerAge)+"\n\t\t\t\t")])]),a("div",{staticClass:"buy",staticStyle:{"margin-left":"500px"}},[a("h3",[t._v("Buy")]),a("br"),a("at-button",{attrs:{hollow:"",type:"info"},on:{click:t.buy}},[t._v("Amazon.com")])],1)]),a("div",{staticClass:"comments"},[a("h2",[t._v("Short Comments...")]),a("at-button",{attrs:{type:"primary",icon:"icon-edit"},on:{click:function(e){t.showAdd=!0}}},[t._v("Write a Short Review")]),a("br"),a("br"),a("at-table",{attrs:{columns:t.cols,data:t.commentList,border:!1}})],1)]),a("at-modal",{attrs:{title:"Short Review"},on:{"on-confirm":t.handleConfirm},model:{value:t.showAdd,callback:function(e){t.showAdd=e},expression:"showAdd"}},[a("div",{staticClass:"form"},[a("div",{staticClass:"form-item"},[a("div",{staticClass:"label"},[t._v("Content")]),a("at-textarea",{model:{value:t.comment.content,callback:function(e){t.$set(t.comment,"content",e)},expression:"comment.content"}})],1),a("br"),a("br"),a("div",{staticClass:"form-item"},[a("div",{staticClass:"label"},[t._v("Score")]),a("at-rate",{model:{value:t.comment.score,callback:function(e){t.$set(t.comment,"score",e)},expression:"comment.score"}})],1)])])],1)},vt=[];a("6762"),a("2fdb");function yt(t,e){var a=Object.keys(t);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(t);e&&(n=n.filter((function(e){return Object.getOwnPropertyDescriptor(t,e).enumerable}))),a.push.apply(a,n)}return a}function bt(t){for(var e=1;e<arguments.length;e++){var a=null!=arguments[e]?arguments[e]:{};e%2?yt(Object(a),!0).forEach((function(e){Object(U["a"])(t,e,a[e])})):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(a)):yt(Object(a)).forEach((function(e){Object.defineProperty(t,e,Object.getOwnPropertyDescriptor(a,e))}))}return t}var wt={data:function(){return{game:{},id:"",userGames:[],showAdd:!1,comment:{},commentList:[],cols:[{title:"Reviewer",key:"reviewerName",render:function(t,e){return t("User",{props:{userInfo:e.item.reviewer}})}},{title:"Content",key:"content"},{title:"Score",key:"score",render:function(t,e){return t("AtRate",{props:{disabled:!0,value:e.item.score}},e.item.discuss)}},{title:"Time",key:"postTime"}]}},created:function(){this.id=this.$route.query.id,this.getGameInfo(),this.getUserGame(),this.getComments()},computed:{isCollected:function(){return this.userGames.includes(this.id)}},methods:{goForum:function(){this.$router.push("/page/club?id="+this.id)},getGameInfo:function(){var t=this;this.$http({url:"/game/view",method:"get",data:{gameId:this.id}}).then((function(e){t.game=e}))},getGameImg:function(){this.$http({url:"/game/getImage",method:"get",data:{gameId:this.id}}).then((function(t){console.log(t)}))},getUserGame:function(){var t=this;this.$http({url:"/game/viewUserGame",method:"get",data:{userId:sessionStorage.getItem("userId"),pageSize:9999999,pageNo:1}}).then((function(e){e.data&&(t.userGames=e.data.map((function(t){return t.id})))}))},del:function(){var t=this;this.$http({url:"/game/delToUser",method:"delete",data:{gameId:this.id,userId:sessionStorage.getItem("userId")}}).then((function(e){t.getUserGame(),t.$Notify({title:"Success",type:"success"})}))},collect:function(){var t=this;this.$http({url:"/game/addToUser",method:"post",data:{gameId:this.id,userId:sessionStorage.getItem("userId")}}).then((function(e){t.getUserGame(),t.$Notify({title:"Success",type:"success"})}))},handleConfirm:function(){var t=this;this.$http({url:"/review/post",method:"post",data:bt({reviewerId:sessionStorage.getItem("userId"),gameId:this.id,anonymous:0},this.comment)}).then((function(e){t.getComments(),t.showAdd=!1,t.comment={}}))},buy:function(){window.open("https://www.amazon.co.uk/s?k="+encodeURI(this.game.gameName))},getComments:function(){var t=this;this.$http({url:"/review/viewGameReview",method:"get",data:{gameId:this.id,pageSize:9999999,pageNo:1}}).then((function(e){t.commentList=e.data.map((function(t){return bt({},t,{reviewerName:t.reviewer.userName,postTime:new Date(t.postTime).toLocaleString(),targetUserId:t.reviewer.id})}))}))}}},_t=wt,It=(a("a0b3"),Object(i["a"])(_t,gt,vt,!1,null,"a5661f18",null)),Ct=It.exports;n["default"].use(u["a"]);var St=new u["a"]({routes:[{path:"/",name:"login",component:g},{path:"/register",name:"register",component:I},{path:"/page",name:"page",component:ht,redirect:"/page/home",children:[{path:"/page/home",name:"home",component:q},{path:"/page/game",name:"game",component:Ct},{path:"/page/my",name:"my",component:K},{path:"/page/friends",name:"friends",component:et},{path:"/page/club",name:"club",component:lt}]}]}),Pt=a("2f62");n["default"].use(Pt["a"]);var $t=new Pt["a"].Store({state:{},mutations:{},actions:{}}),Ot=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("at-tooltip",{attrs:{placement:"top",content:t.content}},[a("at-button",{attrs:{size:"small",type:"text"},on:{click:t.sendReq}},[t._v(t._s(t.userInfo.userName))])],1)},kt=[],xt={computed:{isYourself:function(){return this.userInfo.id===sessionStorage.getItem("userId")},content:function(){return this.isYourself?"Yourself":"Send a friend request"}},props:{userInfo:{type:Object}},methods:{sendReq:function(){var t=this;this.isYourself||this.$http({url:"/friendship/sendFriendRequest",method:"post",data:{senderId:sessionStorage.getItem("userId"),receiverId:this.userInfo.id}}).then((function(){t.$Notify.success({title:"Success"})}))}}},Nt=xt,jt=Object(i["a"])(Nt,Ot,kt,!1,null,null,null),Rt=jt.exports,At=a("44e4"),Tt=a.n(At),Dt=(a("c58f"),a("96cf"),a("3b8d")),Et=(a("6b54"),"http://www.cog.codes/api"),Gt=function(t){var e=t.url,a=t.method,s=t.data,r=Et+e,i=a.toUpperCase(),o={headers:{"content-type":"application/json"},method:i};if("GET"===i||"DELETE"===i||"PUT"===i){var c=new URLSearchParams;for(var l in s)c.append(l,s[l]);r="".concat(r).concat(r.includes("?")?"&":"?").concat(c.toString())}else o.body=JSON.stringify(s);var u=!1;return fetch(r,o).then(function(){var t=Object(Dt["a"])(regeneratorRuntime.mark((function t(e){var a;return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:return u=e.ok,a={},t.prev=2,t.next=5,e.json();case 5:a=t.sent,t.next=10;break;case 8:t.prev=8,t.t0=t["catch"](2);case 10:return t.abrupt("return",a);case 11:case"end":return t.stop()}}),t,null,[[2,8]])})));return function(e){return t.apply(this,arguments)}}()).then((function(t){if(u)return t;throw n["default"].prototype.$Notify({title:"Oops",message:t.message||"Something went Wrong",type:"error"}),Error(t.message)}))},Ft=Gt;n["default"].use(Tt.a),n["default"].component("User",Rt),n["default"].config.productionTip=!1,n["default"].prototype.$http=Ft,new n["default"]({router:St,store:$t,render:function(t){return t(l)}}).$mount("#app")},"5c48":function(t,e,a){},6375:function(t,e,a){},"65f2":function(t,e,a){},"6a32":function(t,e,a){},"7c55":function(t,e,a){"use strict";var n=a("5c48"),s=a.n(n);s.a},"88a2":function(t,e,a){"use strict";var n=a("6a32"),s=a.n(n);s.a},a0b3:function(t,e,a){"use strict";var n=a("6375"),s=a.n(n);s.a},a5b1:function(t,e,a){"use strict";var n=a("b30d"),s=a.n(n);s.a},b30d:function(t,e,a){},b588:function(t,e,a){},b6e9:function(t,e,a){},b9ae:function(t,e,a){},bb88:function(t,e,a){},c08b:function(t,e,a){"use strict";var n=a("b588"),s=a.n(n);s.a},c44e:function(t,e,a){"use strict";var n=a("fd2b"),s=a.n(n);s.a},c6f8:function(t,e,a){},fcbd:function(t,e,a){"use strict";var n=a("c6f8"),s=a.n(n);s.a},fd2b:function(t,e,a){}});
//# sourceMappingURL=app.e632cf36.js.map