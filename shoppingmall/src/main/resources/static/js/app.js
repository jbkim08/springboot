$(function () {
  //alert(1); 테스트 완료
  $('a.deleteConfirm').click(function () {
    if (!confirm('삭제하겠습니까?')) return false; // 취소시 삭제 안됨
  });
});
