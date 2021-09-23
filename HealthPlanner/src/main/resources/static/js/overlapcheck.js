function registerCheck() {
	if ($.trim($('#mbr_id').val()) == '') {
		alert("아이디를 입력해주세요.");
		return false;
	}

	var pwd = document.getElementById("mbrPw")
	

	if ($.trim($('#mbr_pw').val()) == '') {
		alert("비밀번호를 입력해주세요.");
		return false;
	}
	
	/*var pwdCheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
	
	if(!pwdCheck.test(pwd.value))
	{
		alert("비밀번호는 영문자+숫자+특수문자 조합으로 8~25자리 사용해야 합니다.");
		pwd.focus();
		return false;
	}*/
	
	

	if ($.trim($('#mbr_nm').val()) == '') {
		alert("이름을 입력해주세요.");
		return false;
	}
	
	if ($.trim($('#mbr_birth').val()) == '') {
		alert("생년월일을 입력해주세요.");
		return false;
	}
	if ($.trim($('#mbr_email').val()) == '') {
		alert("이메일을 입력해주세요.");
		return false;
	}
	if ($.trim($('#mbr_pw_answer').val()) == '') {
			alert("비밀번호힌트를 입력해주세요.");
			return false;
		}

	if (confirm("회원가입을 하시겠습니까?")) {
		alert("회원가입이 완료되었습니다. 감사합니다.");
		$("form").submit();	}

}

/*function loginCheck()
{
	
	const loginid = document.getElementById('mbr_id').value;
	alert(loginid);
	return loginid;
	

}*/


/* 아이디 중복 체크 : ajax 비동기처리 */
function idCheck() {

	var mbr_id = $('#mbr_id').val();
	
	if (mbr_id.search(/\s/) != -1) {
		alert("아이디에는 공백이 들어갈 수 없습니다.");
	} else {
		if (mbr_id.trim().length != 0) {
			
			$.ajax({
				async: true,
				type: 'POST',
				data: mbr_id,
				url: "idCheck.do",
				dataType: "json",
				contentType: "application/json; charset=UTF-8",
				success: function(count) {
					if (count > 0) {
						alert("해당 아이디 존재");
						$('#submit').attr("disabled", "disabled");
						window.location.reload();
					} else {
						alert("사용가능 아이디");
						$('#submit').removeAttr("disabled");
					}
				},
				error: function(error) {
					alert("1아이디를 입력해주세요.");
				}
			});
		} else {
			alert("아이디를 입력해주세요.");
		}
	}
}

function emailCheck() {

	var mbr_email = $('#mbr_email').val();
	
	if (mbr_email.search(/\s/) != -1) {
		alert("이메일에는 공백이 들어갈 수 없습니다.");
	} else {
		if (mbr_email.trim().length != 0) {
			
			$.ajax({
				async: true,
				type: 'POST',
				data: mbr_email,
				url: "emailCheck.do",
				dataType: "json",
				contentType: "application/json; charset=UTF-8",
				success: function(emailcount) {
					if (emailcount > 0) {
						alert("해당 이메일 존재");
						$('#submit').attr("disabled", "disabled");
						window.location.reload();
					} else {
						alert("사용가능 이메일");
						$('#submit').removeAttr("disabled");
					}
				},
				error: function(error) {
					alert("이메일을 입력해주세요.");
				}
			});
		} else {
			alert("이메일을 입력해주세요.");
		}
	}
}

