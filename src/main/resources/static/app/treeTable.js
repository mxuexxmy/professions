/**
 * 选中父项时，同时选中子项
 * @param datas 所有的数据
 * @param row 当前数据
 * @param id id 字段名
 * @param pid 父id字段名
 */
function selectChilds(datas,row,id,pid,checked) {
    for(var i in datas){
        if(datas[i][pid] == row[id]){
            datas[i].check=checked;
            selectChilds(datas,datas[i],id,pid,checked);
        };
    }
}

function selectParentChecked(datas,row,id,pid){
    for(var i in datas){
        if(datas[i][id] == row[pid]){
            datas[i].check=true;
            selectParentChecked(datas,datas[i],id,pid);
        };
    }
}

function test() {
    var selRows = $table.bootstrapTable("getSelections");
    if(selRows.length == 0){
        alert("请至少选择一行");
        return;
    }

    var postData = "";
    $.each(selRows,function(i) {
        postData +=  this.id;
        if (i < selRows.length - 1) {
            postData += "， ";
        }
    });
    alert("你选中行的 id 为："+postData);

}

function add(id) {
    alert("add 方法 , id = " + id);
}
function del(id) {
    alert("del 方法 , id = " + id);
}
function update(id) {
    alert("update 方法 , id = " + id);
}