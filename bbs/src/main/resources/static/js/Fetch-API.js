/**
 * Fetch API를 사용해 AJAX 로
 * 쉽게 GET, POST, PUT, DELETE 하는 라이브러리
 * 비동기를 async 메소드 , await 를 이용해 대기후 결과 리턴함
 **/

//alert('fetch js 로드됨');

class EasyHTTP {
  // GET
  async get(url) {
    const response = await fetch(url); // ajax 통신으로 결과 받기
    const resData = await response.json(); // 결과에서 제이슨 데이터를 JS 객체로 변환
    return resData; // JSON 데이터 리턴
  }

  // POST 생성(입력)
  async post(url, data) {
    const response = await fetch(url, {
      method: 'POST',
      headers: {
        'Content-type': 'application/json', //보내는 데이터 json 타입 선언
      },
      body: JSON.stringify(data), //js object(객체)를 json타입으로 변환
    });

    const resData = await response.json();
    return resData;
  }

  // PUT 업데이트
  async put(url, data) {
    const response = await fetch(url, {
      method: 'PUT',
      headers: {
        'Content-type': 'application/json',
      },
      body: JSON.stringify(data),
    });

    const resData = await response.json();
    return resData;
  }

  // DELETE 삭제
  async delete(url) {
    const response = await fetch(url, {
      method: 'DELETE',
      headers: {
        'Content-type': 'application/json',
      },
    });

    const resData = await '데이터가 삭제됨...';
    return resData;
  }
}
