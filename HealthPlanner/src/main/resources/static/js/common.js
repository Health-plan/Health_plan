
//자료형에 상관없이 값이 비어있는지 확인
function isEmpty(value){
	if(value == 0 || value == '' || value == undefined || (value != null && typeof value == "object" && !Object.keys(value).length)){
		return true;
	}
	
	return false;
}

//문자열의 마지막 문자의 종성을 반환
function charToUnicode(str){
	return(str.charCodeAt(str.length - 1) - 0xAC00) % 28; //0xAC00부터 완전한 한개의 글씨가 시작됨
}


//field의 값이 올바는 형식인지 체크(정규표현식 사용)
function isValid(field, fieldName, focusField){
	
	if(isEmpty(field.value) == true){
		/* 종성으로 조사(을 또는 를) 구분*/
		var message = (charToUnicode(fieldName) > 0) ? fieldName + "을 확인해 주세요." : fieldName + "를 확인해 주세요.";
		field.focus();
		alert(message);
		return false;
	}
	
	return true;
}