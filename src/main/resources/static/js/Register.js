/*
아이디 중복 확인용 메소드
*/
$(function(){
    var idChk = false;
    var idDuplication = false;
    var pwChk = false;
    var nameChk = false;
    var emailChk = false;
    var phoneChk = false;

    /*
    Auth : 박치원
    title : 아이디 정렬
    Desc : 아이디에 맞는 형식대로 입력을 할 경우 정상 처리 될 수 있도록 점검 한다.
    */
    $('#idError').hide();
    //아이디 유효성 검사 숫자, 영문만 입력 가능 최소 5자리
    var getIdChk = /^[0-9a-z]+.{4,15}$/;
    $('#id').keyup(function (){
        var id = $('#id').val();
        if(getIdChk.test(id)){
            $('#id').addClass("is-valid");
            $('#id').removeClass("is-invalid");
            idChk = true;
        } else {
            $('#id').focus();
            $('#id').addClass("is-invalid");
            $('#id').removeClass("is-valid");
            idChk = false;
        }
    });

    /*
    Auth : 박치원
    title : 아이디 중복 검색
    Desc : 백단 처리를 위한 아이디 중복 검색
    */
    $('#idSuccess').hide();
    $('#idFail').hide();
    $('#idError').hide();
    /*아이디 중복 검색*/
    $('#idCheck').click(function () {
        var data = {}
        data["Id"] = $('#Id').val();

        $.ajax({
            contentType: 'application/json',
            datatype: 'json',
            data: JSON.stringify(data),
            url: 'id_Chk',
            type: 'POST',
            success: function (response) {
                if (response == 'success') {
                    $('#idSuccess').show();
                    $('#idFail').hide();
                    $('#idCheckFail').hide();
                    idDuplication = true;
                } else {
                    $('#Id').focus();
                    $('#idFail').show();
                    $('#idSuccess').hide();
                    idDuplication = false;
                }
            },
            error: function () {
                $('#idError').show();
                $('#idFail').hide();
                $('#idSuccess').hide();
                $('#Id').focus();
                idDuplication = false;
            }
        })
    });
    /*
    Auth : 박치원
    title : password 정렬
    Desc : password에 맞는 형식대로 입력을 할 경우 정상 처리 될 수 있도록 점검 한다.
    */
    var getPasswordChk = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{5,15}$/;
    $('#password').keyup(function (){
        var password = $('#password').val();
        if(getPasswordChk.test(password)){
            $('#password').addClass("is-valid");
            $('#password').removeClass("is-invalid");
        }else{
            $('#password').removeClass("is-valid");
            $('#password').addClass("is-invalid");
        }
    });
    /*
    Auth : 박치원
    title : password 점검
    Desc : password 확인 용도
    */
    $('#passwordChk').keyup(function (){
        var password1 = $('#password').val();
        var password2 = $('#passwordChk').val();

        if(password1 != "" && password2 != ""){
            if(password1 == password2){
                $('#passwordChk').removeClass("is-invalid");
                $('#passwordChk').addClass("is-valid");
                pwChk = true;
            } else {
                $('#passwordChk').removeClass("is-valid");
                $('#passwordChk').addClass("is-invalid");
                pwChk = false;
            }
        }
    });
    /*
    Auth : 박치원
    title : 이름 정렬
    Desc : 이름에 맞는 형식대로 입력을 할 경우 정상 처리 될 수 있도록 점검 한다.
    */
    var getName = /^[가-힣]+.{0,30}$/;
    $('#name').keyup(function (){
       var name = $('#name').val();
       if(name == null){
           $('#name').addClass("is-invalid");
           $('#name').removeClass("is-valid");
           $('#name').focus();
           nameChk = false;
       } else {
           if(getName.test(name)){
               $('#name').addClass("is-valid");
               $('#name').removeClass("is-invalid");
               nameChk = true;
           } else {
               $('#name').addClass("is-invalid");
               $('#name').removeClass("is-valid");
               $('#name').focus();
               nameChk = false;
           }
       }
    });
    /*
    Auth : 박치원
    title : 이메일 정렬
    Desc : 이메일에 맞는 형식대로 입력을 할 경우 정상 처리 될 수 있도록 점검 한다.
    */
    var getMail = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
    $('#email').keyup(function (){
        var email = $('#email').val();
        if(email == null){
            $('#email').addClass("is-invalid");
            $('#email').removeClass("is-valid");
            $('#email').focus();
            emailChk = false;
        } else {
            if(getMail.test(email)){
                $('#email').addClass("is-valid");
                $('#email').removeClass("is-invalid");
                emailChk = true;
            } else {
                $('#email').addClass("is-invalid");
                $('#email').removeClass("is-valid");
                $('#email').focus();
                emailChk = false;
            }
        }
    });
    /*
    Auth : 박치원
    title : 전화번호 정렬
    Desc : common에 전화 번호 정렬로 전화번호를 정렬하고 비어있을 경우 오류 표시를 한다.
    */
    $('#phone').keyup(function (){
        var phone = $('#phone').val();
        if (phone == null){
            $('#phone').addClass("is-invalid");
            $('#phone').removeClass("is-valid");
            $('#phone').focus();
            phoneChk = false;
        } else if(PhoneNumberSetting(phone).length == 13) {
            $('#phone').val(PhoneNumberSetting(phone));
            $('#phone').addClass("is-valid");
            $('#phone').removeClass("is-invalid");
            phoneChk = true;
        } else {
            $('#phone').addClass("is-invalid");
            $('#phone').removeClass("is-valid");
            phoneChk = false;
        }
    });

    /*
    Auth : 박치원
    title : 전화번호 정렬
    Desc : common에 전화 번호 정렬로 전화번호를 정렬하고 비어있을 경우 오류 표시를 한다.
    */
    $('#idCheckFail').hide();
    $('#Register').click(function (){
        if(!idChk){
            $('#id').addClass("is-invalid");
            $('#id').removeClass("is-valid");
            $('#id').focus();
        } else if(!idDuplication){
            $('#idCheckFail').show();
        } else if (!pwChk){
            $('#password').addClass("is-invalid");
            $('#password').removeClass("is-valid");
            $('#password').focus();
            $('#passwordChk').addClass("is-invalid");
            $('#passwordChk').removeClass("is-valid");
        } else if (!nameChk){
            $('#name').addClass("is-invalid");
            $('#name').removeClass("is-valid");
            $('#name').focus();
        } else if(!emailChk){
            $('#email').addClass("is-invalid");
            $('#email').removeClass("is-valid");
            $('#email').focus();
        } else if(!phoneChk){
            $('#phone').addClass("is-invalid");
            $('#phone').removeClass("is-valid");
            $('#phone').focus();
        } else {
            var data = {}
            data["id"] = $('#Id').val();
            data["password"] = $('#password').val();
            data["name"] = $('#name').val();
            data["email"] = $('#email').val();
            data["phone"] = $('#phone').val();
            data["postcode"] = $('#postcode').val();
            data["roadAddress"] = $('#roadAddress').val();
            data["detailAddress"] = $('#detailAddress').val();

            $.ajax({
                contentType: 'application/json',
                datatype: 'json',
                data: JSON.stringify(data),
                url: 'Register',
                type: 'POST',
                success: function (response) {
                    if (response == 'success') {
                        $('#idSuccess').show();
                        $('#idFail').hide();
                        $('#idCheckFail').hide();
                        idDuplication = true;
                    } else {
                        $('#Id').focus();
                        $('#idFail').show();
                        $('#idSuccess').hide();
                        idDuplication = false;
                    }
                },
                error: function () {
                    $('#idError').show();
                    $('#idFail').hide();
                    $('#idSuccess').hide();
                    $('#Id').focus();
                    idDuplication = false;
                }
            })
        }
    });
})
/*
Auth : 박치원
title : 주소 POST코드, 상세 주소 내용 불러오기
Desc : kkao API로 post코드 및 상세 주소 내용 불러오는 용도
*/
function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("roadAddress").value = roadAddr;
        }
    }).open();
}