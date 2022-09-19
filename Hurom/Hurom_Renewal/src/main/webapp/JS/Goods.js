$('#selectOption').change(function(){
    $('#imgchg').attr("src", "../image/Sub/" + $(this).val()+".webp");
});
// 이미지 체인지

function dis() {
    if($('#dis').css('display') == 'none') {
        $('#dis').show();
        return false;
    } else {
        $('#dis').hide();
        return false;
    };
}; 
// 조건별배송 모달창
    

$('#dis img').on('click', function () {
    $('#dis').hide();
});
// 조건별배송 모달창 x 눌렀을 때 모달 안 보이게 설정