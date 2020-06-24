/**
 * 
 */

let userAgentString =  navigator.userAgent; 

let IExplorerAgent = userAgentString.indexOf("MSIE") > -1 || userAgentString.indexOf("rv:") > -1;

if(IExplorerAgent){
	alert("Você terá uma melhor experiência usando Google Chrome ou Microsoft Edge")
	
}

var oReq = new XMLHttpRequest();

oReq.open("GET", "getUserList");
oReq.onreadystatechange = respfunc
oReq.send();

function respfunc(){
	if(oReq.readyState == 4 && oReq.status == 200){
		splitResponse(oReq.responseText)
	}	
}

function splitResponse(nomes){
	let newNomes = nomes.split(',')
	let selElem = document.getElementById('Lista')
	
			
	for (i = 0; i < newNomes.length; i++ ){		
		let opt = document.createElement('option')	
		opt.value = newNomes[i]
		opt.appendChild(document.createTextNode(newNomes[i]))
		document.getElementById('Lista').disabled = false		
		
		selElem.appendChild(opt)
		document.getElementById('toogleUser').checked = false
		
	}	
}

function toogleUserList(){	
	
		if(document.getElementById('toogleUser').checked == true){
			document.getElementById('Lista').disabled = true			
		}else{
			document.getElementById('Lista').disabled = false			
		}	
}