var xhr = new XMLHttpRequest();
      xhr.open('GET', 'http://botnet.artificial.engineering:80/api/Status');
      xhr.responseType = 'json';
      xhr.setRequestHeader('Token', 'my-token-123');
      xhr.onload = function() {
        var data = xhr.response;
        if (data !== null) {
          console.log(data); // Parsed JSON object
        }
      };
      xhr.send(null);

function myFunction(data){
var obj =JSON.parse(data);

for(int i=0;i<var.length;i++){
var table=document.getElementById("table");
var len=table.rows.length;

var row=table.insertRow(len);
var cell1 = row.insertCell(0).innerHTML("'obj[i].workload'");
var cell2 = row.insertCell(1).innerHTML("'obj[i].ip'");
var cell3 = row.insertCell(2).innerHTML("'obj[i].id'");
var cell4 = row.insertCell(3).innerHTML("<label class="switch"><input type="checkbox"> <div class="slider round"></div></label>");


}


}
