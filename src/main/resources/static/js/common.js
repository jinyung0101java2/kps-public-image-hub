
const func = {
	harborUrl : URI_REQUEST_HARBOR_API,
	harborDomain : URI_REQUEST_HARBOR_DOMAIN,
	url : URI_REQUEST_CP_API,
	catalogUrl : URI_REQUEST_CATALOG_API,
	chaosUrl : URI_REQUEST_CHAOS_API,
	ui : 'http://localhost:8091/',
	nameLoad : new function(){},
	clusterData:  new Object(),
	nameData : new Object(),
	createIm : '',
	depth1 : '',
	depth2 : '',

	init(depth1, depth2){
		func.depth1 = depth1;
		func.depth2 = depth2;

		// Locale Language 조회
		func.getLocaleLang();

		// navigation 초기 선택 설정
		if(depth1.length >= 0){
			//depth1 toggle on
			document.querySelector('[aside_d1='+depth1+']').classList.toggle('on', true);
			// depth2 toggle on
			document.querySelector('[aside_d2='+depth2+']').classList.toggle('on', true);
		}

		// navigation height 설정
		var navSub = document.querySelector('nav').querySelectorAll('.sub');
		for(var i=0; i<=navSub.length-1; i++){
			var childSum = navSub[i].childElementCount;
			navSub[i].style.height = (childSum*35+30)+((childSum-1)*10)+'px';
		};

		func.userCheck();
		func.event();
	},

	event(){
		// navigation
		var nav = document.querySelector('nav').querySelectorAll('.dep01');

		for(var i=0; i<=nav.length-1; i++){
			nav[i].addEventListener('click', (e) => {
				e.stopPropagation();

				for(var j=0; j<=nav.length-1; j++){
					nav[j].parentNode.classList.toggle('on', false);
				};

				e.target.parentNode.classList.toggle('on', true);
			}, false);
		};

		// search
		if(document.getElementById('search') != null){
			document.getElementById('search').addEventListener('click', (e) => {
				if(e.target.parentNode.classList != 'on'){
					e.target.parentNode.classList.toggle('on');
				} else {
					if(document.getElementById('searchText').value != ''){
						IS_SEARCH = true;
						func.nameLoad();
					};
				}
			}, false);

			document.getElementById('searchText').onkeydown = function(event) {
				if(event.keyCode === 13){
					IS_SEARCH = true;
					func.nameLoad();
				};
			};

			document.getElementById('searchText').onkeyup = function(event) {
				document.getElementById('searchText').value = document.getElementById('searchText').value.replace( /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/g,'');
			};
		};

		// logout event
		document.getElementById('logout').addEventListener('click', (e) => {
			func.alertPopup('Sign Out', MSG_WANT_TO_SIGN_OUT + '<br><p id="logout-sub">' + MSG_INTEGRATED_SIGN_OUT_TAKES_PLACE + '</p>', true, MSG_CONFIRM, func.logout);
		}, false);

	},

	logout(){
		sessionStorage.clear();
		movePage(URI_CP_LOGOUT);
	},

	proCreate(title, btnName){

		var html = `<div class="modal-wrap" id="modal">
			<div class="modal midium" style="width: 576px;height: 304px">
				<h5>${title}</h5>
					<dl>
						<dt>
							<label for="name">Project Name</label>
						</dt>
						<dd>
							<input id="name" type="text" style="width: 90%; border: 1px solid #ebebeb; height: 50px; line-height: 48px; padding-left: 18px; margin-left: 1px; font-size: 18px;">
						</dd>
					</dl>
					<dl>
						<dt style="line-height: 50px">
							<label for="access">Access Level</label>
						</dt>
						<dd style="padding: 15px 275px 0 0;">
							<input id="access" type="checkbox"/>
							<label for="access" style="padding-left: 27px; position: relative; cursor: pointer;"></label>
							<span style="padding-left: 5px">Public</span>
						</dd>
					</dl>
				<a class="confirm" href="javascript:;">${btnName}</a>
				<a class="close" href="javascript:;">`+ MSG_CLOSE + `</a>
			</div>
		</div>`;

		func.appendHtml(document.getElementById('wrap'), html, 'div');

		document.getElementById('modal').querySelector('.close').addEventListener('click', (e) => {
			document.getElementById('wrap').removeChild(document.getElementById('modal'));
		}, false);

		document.getElementById('modal').querySelector('.confirm').addEventListener('click', (e) => {

			let name = document.getElementById('name').value
			let access = document.getElementById('access').checked

			if (name === '') {
				document.getElementById('name').focus()
			} else {
				document.getElementById('wrap').removeChild(document.getElementById('modal'));

				var sendData =  {"project_name":name,"metadata":{"public":JSON.stringify(access)},"storage_limit": null,"registry_id": null};

				func.saveHarborData('POST', `${func.harborUrl}api/v2.0/projects`, JSON.stringify(sendData), true, 'application/json', func.refresh);
			}
		}, false);
	},

	tagCreate(title, btnName){
		var html = `<div class="modal-wrap" id="modal">
			<div class="modal midium" style="width: 576px;height: 250px">
				<h5>${title}</h5>
					<dl>
						<dt>
							<label for="tagName">Tags Name</label>
						</dt>
						<dd>
							<input id="tagName" type="text" style="width: 90%; border: 1px solid #ebebeb; height: 50px; line-height: 48px; padding-left: 18px; margin-left: 1px; font-size: 18px;">
						</dd>
					</dl>
				<a class="confirm" href="javascript:;">${btnName}</a>
				<a class="close" href="javascript:;">`+ MSG_CLOSE + `</a>
			</div>
		</div>`;

		func.appendHtml(document.getElementById('wrap'), html, 'div');

		document.getElementById('modal').querySelector('.close').addEventListener('click', (e) => {
			document.getElementById('wrap').removeChild(document.getElementById('modal'));
		}, false);

		document.getElementById('modal').querySelector('.confirm').addEventListener('click', (e) => {
			let name = document.getElementById('tagName').value
			if (name === '') {
				document.getElementById('tagName').focus()
			} else {
				document.getElementById('wrap').removeChild(document.getElementById('modal'));

				var sendData =  {"name":name};
				func.saveHarborData('POST', `${func.harborUrl}api/v2.0/projects/${sessionStorage.getItem('repositoryName')}/repositories/${sessionStorage.getItem('imageName')}/artifacts/${sessionStorage.getItem('artifactsName')}/tags`, JSON.stringify(sendData), true, 'application/json', func.refresh);
			}

		}, false);
	},

	create(title, url, name){
		var createYamlAreaHeight = "380px";
		var html = `<div class="modal-wrap" id="modal">
			<div class="modal large">
				<h5>${title}</h5>
				<dl>
					<dt>Namespace</dt>
					<dd>
						<fieldset>
							<select id="createName">
							</select>
						</fieldset>
					</dd>
				</dl>`;
		if(IS_TRAFFIC_POLICY_MANAGED) {
			createYamlAreaHeight = "360px";
			html+=`<dl style="height:25px; margin:15px 0 -15px 0;">
						<dt></dt>
						<dd style="text-align:left; display:flex;">
							<label class="container" style="font-size:14px; width:auto;">${MSG_ALLOW_TRAFFIC_BETWEEN_NAMESPACES_CHK}
                        		<input type="checkbox" id="allowTrafficChk">
                        		<span class="checkmark"></span>
                        	</label>
                        	<div class="tooltip" style="padding: 1px 7px;">
                        		<i class="fa-solid fa-circle-info"></i>
 						 		<span class="tooltiptext_modal">${MSG_ALLOW_TRAFFIC_BETWEEN_NAMESPACES_DETAILS}</span>
							</div>
						</dd>
					  </dl>`;
		}
		html +=`<dl style="margin-top: 20px;">
							<dt>YAML</dt>
							<dd class="createYamlArea" style="text-align: left;">
								<textarea class="codemirror-resource-create-textarea"></textarea>
							</dd>
						</dl>
						<a class="confirm" href="javascript:;">${name}</a>
						<a class="close" href="javascript:;">`+ MSG_CLOSE + `</a>
					</div></div>`;

		func.appendHtml(document.getElementById('wrap'), html, 'div');
		CodeMirror.fromTextArea($(".codemirror-resource-create-textarea")[0], {
			value: "",
			theme: "default",
			scrollbarStyle: "simple",
			mode: "text/x-yaml",
			lineNumbers: true,
			lineWrapping: true,
		}).setSize("660px", createYamlAreaHeight);

		for(var i=0; i<=func.nameData.items.length-1; i++){
			var namespace = func.nameData.items[i].cpNamespace;
			if(namespace != NAMESPACE_ALL_VALUE){
				var html = `<option value="${namespace}">${namespace}</option>`;
				func.appendHtml(document.getElementById('createName'), html, 'select');
			};
		};

		if(sessionStorage.getItem('nameSpace') == NAMESPACE_ALL_VALUE) {
			document.getElementById('createName').selectedIndex = 0;}
		else {
			document.getElementById('createName').value = sessionStorage.getItem('nameSpace');
		}

		document.getElementById('modal').querySelector('.close').addEventListener('click', (e) => {
			document.getElementById('wrap').removeChild(document.getElementById('modal'));
		}, false);


		document.getElementById('modal').querySelector('.confirm').addEventListener('click', (e) => {
			var createYaml = document.querySelector(".createYamlArea > .CodeMirror").CodeMirror.getValue();
			var allowTraffic = false;
			if(document.getElementById('allowTrafficChk') != null) {
				allowTraffic = document.getElementById('allowTrafficChk').checked;
			}
			sessionStorage.setItem('nameSpace' , document.getElementById('createName').value);
			document.querySelector('.nameTop').innerHTML = sessionStorage.getItem('nameSpace');
			document.getElementById('wrap').removeChild(document.getElementById('modal'));

			var sendData = JSON.stringify ({
				cluster : sessionStorage.getItem('cluster'),
				namespace : sessionStorage.getItem('nameSpace'),
				resourceName : url,
				yaml : createYaml,
				allowTraffic: allowTraffic
			});

			func.saveData('POST', `${func.url}clusters/${sessionStorage.getItem('cluster')}/namespaces/${sessionStorage.getItem('nameSpace')}/${url}`, sendData, true, 'application/json', func.refresh);
		}, false);
	},

	modify(data){
		var html = `<div class="modal-wrap" id="modal">
			<div class="modal large">
				<h5>Modify</h5>
				<dl>
					<dt>Namespace</dt>
					<dd>
						<fieldset>
							<select id="createName" disabled>
							</select>
						</fieldset>
					</dd>
				</dl>
				<dl>
					<dt>YAML</dt>
					<dd class="updateYamlArea" style="text-align: left;">
						<textarea class="codemirror-resource-update-textarea"></textarea>
					</dd>
				</dl>
				<a class="confirm" href="javascript:;">`+ MSG_SAVE +`</a>
				<a class="close" href="javascript:;">`+ MSG_CLOSE + `</a>
			</div>
		</div>`;

		func.appendHtml(document.getElementById('wrap'), html, 'div');
		CodeMirror.fromTextArea($(".codemirror-resource-update-textarea")[0], {
			value: "",
			theme: "default",
			scrollbarStyle: "simple",
			mode: "text/x-yaml",
			lineNumbers: true,
			lineWrapping: true,
		}).setSize("660px", "400px");

		document.querySelector(".updateYamlArea > .CodeMirror").CodeMirror.setValue(data.sourceTypeYaml);
		var namespaceOptions = `<option value="${sessionStorage.getItem('nameSpace')}">${sessionStorage.getItem('nameSpace')}</option>`;
		func.appendHtml(document.getElementById('createName'), namespaceOptions, 'select');

		document.getElementById('modal').querySelector('.close').addEventListener('click', (e) => {
			document.getElementById('wrap').removeChild(document.getElementById('modal'));
		}, false);

		document.getElementById('modal').querySelector('.confirm').addEventListener('click', (e) => {
			var updateYaml = document.querySelector(".updateYamlArea > .CodeMirror").CodeMirror.getValue();
			document.getElementById('wrap').removeChild(document.getElementById('modal'));

			var sendData = JSON.stringify ({
				cluster : sessionStorage.getItem('cluster'),
				namespace : sessionStorage.getItem('nameSpace'),
				resourceName : sessionStorage.getItem('commonName'),
				yaml : updateYaml
			});

			func.saveData('PUT', `${func.url}clusters/${sessionStorage.getItem('cluster')}/namespaces/${sessionStorage.getItem('nameSpace')}/${document.getElementById('modify').getAttribute('data-role')}/${sessionStorage.getItem('commonName')}`, sendData, true, 'application/json', func.refresh);
		}, false);
	},

	// Refresh 토큰 조회 ////////////////////////////////////////////////////////////////
	refreshToken(){
		var request = new XMLHttpRequest();

		request.open('GET', URI_CP_REFRESH_TOKEN, false);
		request.setRequestHeader('Content-type', 'application/json');

		request.onreadystatechange = () => {
			if (request.readyState === XMLHttpRequest.DONE){
				if(request.status === 200){
					// 토큰 업데이트
					sessionStorage.setItem('token' , 'Bearer ' + JSON.parse(request.responseText).accessToken);
				} else {
					func.alertPopup('ERROR', JSON.parse(request.responseText).detailMessage, true, MSG_CLOSE);
				};
			};
		};

		request.send();
	},

	// Locale Language 조회 ////////////////////////////////////////////////////////////////
	getLocaleLang(){
		var request = new XMLHttpRequest();
		request.open('GET', URL_API_LOCALE_LANGUAGE, false);
		request.setRequestHeader('Content-type', 'application/json');

		request.onreadystatechange = () => {
			if (request.readyState === XMLHttpRequest.DONE){
				if(request.status === 200){
					CURRENT_LOCALE_LANGUAGE = request.responseText;
					setSelectValue('u_locale_lang',request.responseText);
				} else {
					CURRENT_LOCALE_LANGUAGE = LANG_EN;
					setSelectValue('u_locale_lang',LANG_EN);
				};
			};
		};
		request.send();
	},

	// Locale Language 설정 ////////////////////////////////////////////////////////////////
	setLocaleLang(reqUrl){
		var request = new XMLHttpRequest();
		request.open('PUT', reqUrl, false);
		request.setRequestHeader('Content-type', 'application/json');

		request.onreadystatechange = () => {
			if (request.readyState === XMLHttpRequest.DONE){
				if(request.status === 200){
					reloadPage();
				}
			};
		};
		request.send();
	},

	setUserAuthority(cluster, usersList){
		var authority ='';
		for(var i= 0; i < usersList.length; i++) {
			var users = usersList[i];
			if(users.clusterId === cluster) {
				authority = users.userType;
				break;
			}
		}
		var request = new XMLHttpRequest();
		request.open('PUT', URI_API_SET_CLUSTER_AUTHORITY, false);
		request.setRequestHeader('Content-type', 'application/json');

		request.onreadystatechange = () => {
			if (request.readyState === XMLHttpRequest.DONE){
				if(request.status === 200){
				}
			};
		};
		request.send(authority);
	},
	/////////////////////////////////////////////////////////////////////////////////////
	// 데이터 로드 - loadData(method, url, callbackFunction)
	// (전송타입, url, 콜백함수)
	/////////////////////////////////////////////////////////////////////////////////////
	loadData(method, url, header, callbackFunction, list){

		if(url == null) {
			callbackFunction();
			return false;
		}

		var request = new XMLHttpRequest();

		setTimeout(function() {
			request.open(method, url, false);
			request.setRequestHeader('Content-type', header);
			request.setRequestHeader('Authorization', sessionStorage.getItem('accessToken'));
			request.setRequestHeader('uLang', CURRENT_LOCALE_LANGUAGE);
			request.setRequestHeader('Accept-Language', CURRENT_LOCALE_LANGUAGE);


			request.onreadystatechange = () => {
				if (request.readyState === XMLHttpRequest.DONE){
					if(request.status === 200 && request.responseText != ''){
						var resultMessage = JSON.parse(request.responseText).resultMessage;
						var resultCode =  JSON.parse(request.responseText).resultCode;
						var detailMessage = JSON.parse(request.responseText).detailMessage;
						//토큰 만료 검사
						if( resultMessage == 'TOKEN_EXPIRED') {
							func.refreshToken();
							return func.loadData(method, url, header, callbackFunction, list);
						}
						else if(resultMessage == 'TOKEN_FAILED') {
							return func.loadData(method, url, header, callbackFunction, list);
						}
						else if(resultCode != RESULT_STATUS_SUCCESS) {
							if(document.getElementById('loading')){
								document.getElementById('wrap').removeChild(document.getElementById('loading'));
							};
							func.alertPopup('ERROR', detailMessage, true, MSG_CONFIRM, 'closed');
						}
						else {
							callbackFunction(JSON.parse(request.responseText), list);
						}
					} else if(JSON.parse(request.responseText).httpStatusCode === 500){
						sessionStorage.clear();
					};
				};
			};

			request.send(); },0);
	},

	loadHarborData(method, url, header, callbackFunction, list){

		if(url == null) {
			callbackFunction();
			return false;
		}
		var request = new XMLHttpRequest();

		setTimeout(function (name, value) {
			request.open(method, url, false);
			request.setRequestHeader('Content-type', header);
			request.setRequestHeader('Authorization', sessionStorage.getItem('accessToken'));
			request.setRequestHeader('uLang', CURRENT_LOCALE_LANGUAGE);
			request.setRequestHeader('Accept-Language', CURRENT_LOCALE_LANGUAGE);

			request.onreadystatechange = async () => {
				if (request.readyState === XMLHttpRequest.DONE) {
					if (request.status === 200 && request.responseText != '') {
						callbackFunction(JSON.parse(request.responseText), list);

					} else if (JSON.parse(request.responseText).httpStatusCode === 500) {
						sessionStorage.clear();
					};
				};
			};

			request.send(); },0);
	},

	loadCveData(method, url, header, callbackFunction, list){

		if(url == null) {
			callbackFunction();
			return false;
		}
		var request = new XMLHttpRequest();

		setTimeout(function (name, value) {
			request.open(method, url, false);
			request.setRequestHeader('Content-type', header);
			request.setRequestHeader('Authorization', sessionStorage.getItem('accessToken'));
			request.setRequestHeader('uLang', CURRENT_LOCALE_LANGUAGE);
			request.setRequestHeader('Accept-Language', CURRENT_LOCALE_LANGUAGE);

			request.onreadystatechange = async () => {
				if (request.readyState === XMLHttpRequest.DONE) {
					if (request.status === 200 && request.responseText != '') {

						if (url.includes('security/vul?')) {
							sessionStorage.setItem('cveCnt', request.getResponseHeader('x-total-count'))
							func.cveCnt = request.getResponseHeader('x-total-count');
						}

					} else if (JSON.parse(request.responseText).httpStatusCode === 500) {
						sessionStorage.clear();
					};
				};
			};

			request.send(); },0);
	},

	/////////////////////////////////////////////////////////////////////////////////////
	// 데이터 SAVE - saveData(method, url, data, bull, callFunc)
	// (전송타입, url, 데이터, 분기, 콜백함수)
	/////////////////////////////////////////////////////////////////////////////////////
	saveData(method, url, data, bull, header, callFunc){
		func.loading();

		var request = new XMLHttpRequest();

		setTimeout(function() {
			request.open(method, url, false);
			request.setRequestHeader('Content-type', header);
			request.setRequestHeader('Authorization', sessionStorage.getItem('accessToken'));
			request.setRequestHeader('uLang', CURRENT_LOCALE_LANGUAGE);
			request.setRequestHeader('Accept-Language', CURRENT_LOCALE_LANGUAGE);

			request.onreadystatechange = () => {
				if (request.readyState === XMLHttpRequest.DONE){
					if(request.status === 200 && request.responseText != ''){

						//토큰 만료 검사
						if(JSON.parse(request.responseText).resultMessage == 'TOKEN_EXPIRED') {
							func.refreshToken();
							return func.saveData(method, url, data, bull, header, callFunc);
						}
						else if(JSON.parse(request.responseText).resultMessage == 'TOKEN_FAILED') {
							return func.loadData(method, url, header, callbackFunction, list);
						}
						else {
							document.getElementById('wrap').removeChild(document.getElementById('loading'));
							var response = JSON.parse(request.responseText);
							if (response.httpStatusCode == 200) {
								if(response.resultCode == RESULT_STATUS_SUCCESS) {
									func.alertPopup('SUCCESS', response.detailMessage, true, MSG_CONFIRM, callFunc);
								}
								else {
									func.alertPopup('ERROR', response.detailMessage, true, MSG_CONFIRM, 'closed');
								}
							}
							else {
								func.alertPopup('ERROR', response.detailMessage, true, MSG_CONFIRM, 'closed');
							}

						}
					} else {
					};
				};
			};

			request.send(data); }, 0);
	},

	saveHarborData(method, url, data, bull, header, callFunc){
		func.loading();

		var request = new XMLHttpRequest();

		setTimeout(function() {
			request.open(method, url, false);
			request.setRequestHeader('Content-type', header);
			request.setRequestHeader('Authorization', sessionStorage.getItem('accessToken'));
			request.setRequestHeader('uLang', CURRENT_LOCALE_LANGUAGE);
			request.setRequestHeader('Accept-Language', CURRENT_LOCALE_LANGUAGE);

			request.onreadystatechange = () => {
				if (request.readyState === XMLHttpRequest.DONE){
					if(request.status === 200 || request.status === 201) {
						if (method === 'DELETE') {
							func.alertPopup('SUCCESS', MSG_DELETE_COMPLETED, true, MSG_CONFIRM, callFunc);
						} else if (method === 'POST') {
							func.alertPopup('SUCCESS', MSG_CREATION_COMPLETED, true, MSG_CONFIRM, callFunc);
						}
					} else {
						func.alertPopup('ERROR', MSG_ERROR, true, MSG_CONFIRM, 'closed');

						if (document.getElementById('loading')) {
							document.getElementById('wrap').removeChild(document.getElementById('loading'));
						}
					}
				};
			};

			request.send(data); }, 0);
	},

	/////////////////////////////////////////////////////////////////////////////////////
	// 데이터 SAVE - dryRun(method, url, data, bull, callFunc)
	// (전송타입, url, 데이터, 분기, 콜백함수)
	/////////////////////////////////////////////////////////////////////////////////////
	dryRun(method, url, data, bull, header, callFunc){
		func.loading();

		var request = new XMLHttpRequest();

		setTimeout(function() {
			request.open(method, url, false);
			request.setRequestHeader('Content-type', header);
			request.setRequestHeader('Authorization', sessionStorage.getItem('accessToken'));
			request.setRequestHeader('uLang', CURRENT_LOCALE_LANGUAGE);
			request.setRequestHeader('Accept-Language', CURRENT_LOCALE_LANGUAGE);

			request.onreadystatechange = () => {
				if (request.readyState === XMLHttpRequest.DONE){
					if(request.responseText != ''){
						//토큰 만료 검사
						if(JSON.parse(request.responseText).resultMessage == 'TOKEN_EXPIRED') {
							func.refreshToken();
							return func.saveData(method, url, data, bull, header, callFunc);
						}
						else if(JSON.parse(request.responseText).resultMessage == 'TOKEN_FAILED') {
							return func.loadData(method, url, header, callbackFunction, list);
						}
						else {
							document.getElementById('wrap').removeChild(document.getElementById('loading'));
							var response = JSON.parse(request.responseText);
							if (response.httpStatusCode == 200) {
								if(response.resultCode == RESULT_STATUS_SUCCESS) {
									callFunc(response);
								}
								else {
									if(document.getElementById('loading')){
										document.getElementById('wrap').removeChild(document.getElementById('loading'));
									};
									func.alertPopup('ERROR', response.detailMessage, true, MSG_CONFIRM, 'closed');
								}
							}
							else {
								if(document.getElementById('loading')){
									document.getElementById('wrap').removeChild(document.getElementById('loading'));
								};
								func.alertPopup('ERROR', response.detailMessage, true, MSG_CONFIRM, 'closed');
							}

						}
					}
				};
			};

			request.send(data); }, 0);
	},

	/////////////////////////////////////////////////////////////////////////////////////
	// 공통 경고 팝업 - alertPopup(title, text, bull, name, fn)
	// (제목, 문구, 버튼유무, 버튼이름, 콜백함수)
	/////////////////////////////////////////////////////////////////////////////////////
	alertPopup(title, text, bull, name, callback){
		var html = `<div class='modal-wrap' id='alertModal'><div class='modal'><h5>${title}</h5><p>${text}</p>`;
		if(bull){
			html += `<a class='confirm' href='javascript:;'>${name}</a>`;
		};
		html += `<a class='close' href='javascript:;'>` + MSG_CLOSE + `</a></div></div>`;

		if(document.getElementById('alertModal') !== null) {
			document.getElementById('wrap').removeChild(document.getElementById('alertModal'));
		}

		func.appendHtml(document.getElementById('wrap'), html, 'div');

		document.getElementById('alertModal').querySelector('.close').addEventListener('click', (e) => {
			document.getElementById('wrap').removeChild(document.getElementById('alertModal'));
		}, false);

		if(callback){
			document.getElementById('alertModal').querySelector('.confirm').addEventListener('click', (e) => {
				if(callback != 'closed'){
					callback();
				};

				if(!IS_VCHK) {
					document.getElementById('wrap').removeChild(document.getElementById('alertModal'));
				}
			}, false);
		};
	},

	moveToMain() {
		location.href = URI_CP_BASE_URL;
	},

	historyBack(){
		window.history.back();
	},

	refresh(){
		location.href = location.href;
	},

	none() {
		return false;
	},
	loading(){
		var html = `<div id="loading">
						<div class="cubeSet">
							<div class="cube1 cube"></div>
							<div class="cube2 cube"></div>
							<div class="cube4 cube"></div>
							<div class="cube3 cube"></div>
						</div>
					</div>`

		func.appendHtml(document.getElementById('wrap'), html, 'div');
	},

	/////////////////////////////////////////////////////////////////////////////////////
	// html 생성 - appendHtml(target, html, type)
	// (삽입 타겟, html 내용, 타입)
	/////////////////////////////////////////////////////////////////////////////////////
	appendHtml(target, html, type){
		var div = document.createElement(type);
		div.innerHTML = html;
		while (div.children.length > 0){
			target.appendChild(div.children[0]);
		};
	},

	/////////////////////////////////////////////////////////////////////////////////////
	// html 삭제 - removeHtml(target)
	// (타겟 : 타겟의 자식요소 전부 삭제)
	/////////////////////////////////////////////////////////////////////////////////////
	removeHtml(target){
		while(target.hasChildNodes()){
			target.removeChild(target.firstChild);
		};
	},

	/////////////////////////////////////////////////////////////////////////////////////
	// Count UP - 숫카 카운트업
	// (적용 타겟, 적용 숫자)
	/////////////////////////////////////////////////////////////////////////////////////
	countUp(target, num) {
		var cnt = -1;
		var dif = 0;

		var thisID = setInterval(function(){
			if(cnt < num){
				dif = num - cnt;

				if(dif > 0) {
					cnt += Math.ceil(dif / 5);
				};

				target.innerHTML = cnt;
			} else {
				clearInterval(thisID);
			};
		}, 20);
	},

	/////////////////////////////////////////////////////////////////////////////////////
	// 도넛 차트
	// (적용 타겟, 적용 데이터)
	/////////////////////////////////////////////////////////////////////////////////////
	donutChart(target, data){
		var chart = c3.generate({
			bindto: target,
			data: {
				columns: data,
				type : 'donut'
			},
			donut: {
				width: 47
			},
			legend: {
				show: false
			},
			color: {
				pattern: ['#0ca583', '#ffc53e', '#f34111', '#844adb', '#d9d9d9']
			},
		});
	},

	isCollapse(id, collapse) {
		let el =  document.getElementById(id);
		el.classList.toggle('collapse');
		if(collapse) {
			el.title="Collapse Content";
		}else {
			el.title="Expand Content"
		}
	},

	encodeBase64() {
		return CryptoJS.enc.Base64.stringify(CryptoJS.enc.Utf8.parse("admin:Harbor12345"))
	},

	countingSeconds(seconds) {

		if (seconds < 61) {
			return addZero(seconds).replace(/^0+/, '') +' sec'
		}
		// sec
		var hours = Math.floor(seconds/3600)
		var mins = Math.floor((seconds - hours*3600)/60)
		var secs = seconds - hours*3600 - mins*60

		if (hours > 1) {
			return addZero(hours).replace(/^0+/, '') + ' h ' + addZero(mins).replace(/^0+/, '') + ' m ' + addZero(secs).replace(/^0+/, '') + ' sec'
		} else if (hours === 0) {
			return addZero(mins).replace(/^0+/, '') + ' m ' + addZero(secs).replace(/^0+/, '') + ' sec'
		}

		function addZero(num) {
			return ((num < 10) ? '0' : '') + num
		}

	},

	changeTime(time) {
		const create_date_time = time;
		const date = new Date(create_date_time);
		const year = date.getFullYear();
		const month = String(date.getMonth() + 1).padStart(2, "0");
		const day = String(date.getDate()).padStart(2, "0");
		const hours = String(date.getHours()).padStart(2, "0");
		const minutes = String(date.getMinutes()).padStart(2, "0");
		let hoursPm = ``;

		if (hours > 12) {//13 ~ 23
			hoursPm = hours - 12
			return `${year}-${month}-${day} ${hoursPm}:${minutes} PM`;
		} else if (hours === 12) {//12
			return `${year}-${month}-${day} ${hours}:${minutes} PM`;
		} else if (hours < 12) { //0 ~ 11
			return `${year}-${month}-${day} ${hours}:${minutes} AM`;
		}
	},

	differenceInTime(time) {
		const startDate = new Date(time);
		const endDate = new Date();
		const timeDifferenceInMilliseconds = endDate.getTime() - startDate.getTime();
		let result;

		const timeDifferenceInSeconds = timeDifferenceInMilliseconds / 1000;
		const timeDifferenceInMinutes = timeDifferenceInSeconds / 60;
		const timeDifferenceInHours = timeDifferenceInMinutes / 60;
		const timeDifferenceInDays = timeDifferenceInHours / 24;

		if (timeDifferenceInMinutes > 60 && timeDifferenceInHours < 24) {
			result = {
				"time": Math.floor(timeDifferenceInHours),
				"unit": "hour"
			}
		} else if (timeDifferenceInHours > 24) {
			result = {
				"time": Math.floor(timeDifferenceInDays),
				"unit": "day"
			}
		} else if (timeDifferenceInMinutes < 60) {
			result = {
				"time": Math.floor(timeDifferenceInMinutes),
				"unit": "minute"
			}
		}

		return result;
	},

	formatBytes(bytes, decimals) {
		if (bytes === 0) return '0 Byte';

		const k = 1024;
		const sizes = ['Byte', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];

		const i = Math.floor(Math.log(bytes) / Math.log(k));

		return parseFloat((bytes / Math.pow(k, i)).toFixed(2)) + ' ' + sizes[i];
	},

	vulnerabilitiesData(data) {

		let severity = data.severity;
		let total = data.summary.total;
		let fixable = data.summary.fixable;
		let id = data.id;

		return {
			"severity" : severity,
			"total" : total + " Total - " + fixable + " Fixable",
			"id" : id
		}

	},

	cvss3Data(data) {

		let cvssData = "";
		let cvss3Keys = Object.keys(data)
		let cvss3Values = Object.values(data)

		for (var i = 0; i <= cvss3Keys.length - 1; i++) {
			if (Object.keys(cvss3Values[i]).includes("V3Score")) {
				cvssData += cvss3Keys[i] + ": " + cvss3Values[i].V3Score + '\n';
			} else {
				cvssData += cvss3Keys[i] + ":" + '\n';
			}
		}

		return cvssData;
	},

	userCheck() {

	}
	/*userCheck(username, realname, email) {
		console.log(username + ' :: ' + realname + ' :: ' + email)
		func.loadHarborData('GET', `${func.harborUrl}api/v2.0/users`, 'application/json', (e) => {
			if (e.length > 0) {
				for (let i = 0; i <= e.length - 1; i++) {
					console.log(JSON.stringify(e[i]))
					if (e[i].name !== username) {

						let userData = {"email": email, "realname": realname, "comment": "", "password": "", "username": username}
						console.log(JSON.stringify(userData))
						//func.saveData('POST', `${func.harborUrl}api/v2.0/users`, JSON.stringify(userData), true, 'application/json', func.refresh);
					}
				}
			}
		});
	}*/
}