$(function () {
  //alert(1); 테스트 완료
  $('a.deleteConfirm').click(function () {
    if (!confirm('삭제하겠습니까?')) return false; // 취소시 삭제 안됨
  });

  // 페이지 컨텐트 ck 에디터
  if ($('#content').length) {
    //제이쿼리에서 태그선택시 없어도 참이 나오기 때문에 length를 사용
    ClassicEditor.create(document.querySelector('#content')).catch((error) => {
      console.error(error);
    });
  }
  // 상품 설명에 ck 에디터
  if ($('#description').length) {
    ClassicEditor.create(document.querySelector('#description')).catch((error) => {
      console.error(error);
    });
  }
});
