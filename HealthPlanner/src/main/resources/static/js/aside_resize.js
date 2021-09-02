//aside가 contents와 height가 같게 하는 기능

var as = document.getElementById('contents').clientHeight;
document.getElementById('side').style.height = as.toString() + "px";
