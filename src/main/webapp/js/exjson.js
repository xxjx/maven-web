/**
 * 
 */	
function showData(){
		var str='{"GetUserPostByIdResult":{"title":"wo","content":"12","url":"123123"}}';
	    var obj = eval('(' + str + ')'); //  //由JSON字符串转换为JSON对象
	    
	    alert( obj.GetUserPostByIdResult.title);
	    
	}

function change() {
    var content = document.getElementById("search"); 
	window.location.href = "index.jsp?search="+content;
}