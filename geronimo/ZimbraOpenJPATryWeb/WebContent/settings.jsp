<html>
<head>
<title>Settings</title>
<script type="text/javascript">
var intTextBox1=0;
var intTextBox2=0;

//FUNCTION TO ADD TEXT BOX ELEMENT
function addElement1()
{
intTextBox1 = intTextBox1 + 1;
var contentID = document.getElementById('content1');
var newTBDiv = document.createElement('div');
newTBDiv.setAttribute('id','strText1'+intTextBox1);
newTBDiv.innerHTML = "Text "+intTextBox1+": <input type='text' id='" + "box1" +intTextBox1 + "' name='" + intTextBox1 + "'/>";
contentID.appendChild(newTBDiv);
}

//FUNCTION TO REMOVE TEXT BOX ELEMENT
function removeElement1()
{
if(intTextBox1 != 0)
{
var contentID = document.getElementById('content1');
contentID.removeChild(document.getElementById('strText1'+intTextBox1));
intTextBox1 = intTextBox1-1;
}
}

function addElement2()
{
intTextBox2 = intTextBox2+ 1;
var contentID = document.getElementById('content2');
var newTBDiv = document.createElement('div');
newTBDiv.setAttribute('id','strText2'+intTextBox2);
newTBDiv.innerHTML = "Text "+intTextBox2+": <input type='text' id='" + "box2" +intTextBox2 + "' name='" + intTextBox2 + "'/>";
contentID.appendChild(newTBDiv);
}

//FUNCTION TO REMOVE TEXT BOX ELEMENT
function removeElement2()
{
if(intTextBox2 != 0)
{
var contentID = document.getElementById('content2');
contentID.removeChild(document.getElementById('strText2'+intTextBox2));
intTextBox2 = intTextBox2-1;
}
}

function changeVisibility1()
{
document.getElementById("p1").style.visibility="hidden";
document.getElementById("p2").style.visibility="hidden";
}

function changeVisibility2()
{
document.getElementById("p1").style.visibility="visible";
document.getElementById("p2").style.visibility="visible";
document.getElementById("d1").style.display="inherit";
}
function func()
{
	alert("in");
	if (intTextBox1!=0 || intTextBox2!=0)
	{
		alert("in2");
		var contentID = document.getElementById('content3');
		var newTBDiv = document.createElement('div');
		newTBDiv.setAttribute('id','strText3');
		var stringToSet3 = "Text : <input id='box3' name='box3' value='";
		for(var i=1; i<=intTextBox1; i++)
		{
			stringToSet3 = stringToSet3+document.getElementById('box1'+i).value+",";
		}
		stringToSet3 = stringToSet3 + "'type='text'/>";
		newTBDiv.innerHTML = stringToSet3;
		contentID.appendChild(newTBDiv);
		document.getElementById("content3").style.visibility="hidden";
		contentID = document.getElementById('content4');
		newTBDiv = document.createElement('div');
		newTBDiv.setAttribute('id','strText4');
		var stringToSet4 = "Text : <input id='box4' name='box4' value='";
		for(var j=1; j<=intTextBox2; j++)
		{
			stringToSet4 = stringToSet4+document.getElementById('box2'+j).value+",";
		}
		stringToSet4 = stringToSet4 + "'type='text'/>";
		newTBDiv.innerHTML = stringToSet4;
		contentID.appendChild(newTBDiv);
		document.getElementById("content").style.visibility="hidden";
	} 
}
</script>
</head>
<body>
<h1> Settings Page</h1>
<p>You can modify the settings for SMS notification here. The default settings are in enable mode i.e. all the filters are disabled. By checking the permanently disable you can disable this service. While Custom Disable provides you with various filters. Checking the enable mode will lift all the filters again.</p>
<!--hr-->
<form name= "setting" method="post">
<input type="radio" name="Enable-Disable" value="Enable" onclick="changeVisibility1()"
value="Hide paragraph" /> Enable
<br>
<input type="radio" name="Enable-Disable" value="Permanent Disable" onclick="changeVisibility1()"
value="Hide paragraph" /> Permanent Disable
<br>
<input type="radio" name="Enable-Disable" value="Custom Disable" onClick="changeVisibility2()"/> Custom Disable

</form>
<div id= "d1" style="display:none;">
<p id="p1" >Filter by User<!--/p-->
<!--p-->
<br>
<a href="javascript:addElement1();" >Add</a> 
<a href="javascript:removeElement1();" >Remove</a>
</p>
<div id="content1">
</div>
<hr>
<p id = "p2">Filter by Subject Keyword<!--/p-->
<!--p-->
<br>
<a href="javascript:addElement2();" >Add</a> 
<a href="javascript:removeElement2();" >Remove</a>
</p>
</div>
<div id="content2">
</div>
<div id="content3">
</div>
<div id="content4">
</div>
<form name = "savesetting" method="post" onSubmit="return func()">
<input type="submit" value="Save Settings & Proceed to Homepage" />
</form>
<!--form name = "continue" method="post" action="settings.jsp">
<input type="submit" value="Continue With Settings" />
</form-->
<!--div id="d2" style="display:none"-->
<!--form-->
<input type="hidden" name="hidtext" id="hide">
<!--/form-->
<!--/div-->

</body>
</html>
