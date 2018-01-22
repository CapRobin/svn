/*
    获取父节点
        element.parentNode;

    获取子节点:
        element.firstChild;
        element.firstElementChild;
         element.lastChild;
         element.lastElementChild;

     获取兄弟节点
        element.previousSibling
        element.previousElementSibling
         element.nextSibling
         element.nextElementSibling

      获取所有子节点:
           element.childNodes;
           element.children


       获取任意兄弟节点
         element.parentNode.children[index]



 */

/**
 *功能:传入一个父节点,获取第一个元素子节点
 * @param ele
 * @returns {Element|*|Node}
 *
 */
function getFirstChild(ele){
    var node=ele.firstElementChild || ele.firstChild;
    return node;

}
/**
 *
 * 功能: 传入父节点,获取当前父节点的最后一个元素子节点
 * @param ele
 * @returns {Element|*|Node}
 */
function getLastChild(ele){
    return ele.lastElementChild || ele.lastChild;
}
/**
 *
 * 功能: 传入元素对象,返回该元素对象的上一个兄弟元素节点
 * @param ele
 * @returns {Element|*|Node}
 */
function getPreviousdSibling(ele){
    return ele.previousElementSibling || ele.previousSibling;
}
/**
 *
 * 功能: 传入元素对象,返回该元素对象的下一个兄弟元素节点
 * @param ele
 * @returns {Element|*|Node}
 */
function  getNextSibling(ele){
    return ele.nextElementSibling ||ele.nextSibling;
}
/**
 * 功能: 传入元素对象和索引,返回该元素对象的该索引值的兄弟元素节点
 *
 * @param ele
 * @param index
 * @returns {*|HTMLElement}
 */
function getSiblingByIndex(ele,index){
    return ele.parentNode.children[index];
}

/**
 *
 * 功能: 传入元素对象,返回该元素对象的其他所有兄弟元素节点 返回数组
 * @param ele
 * @returns {Array}
 */
function getAllSibling(ele){
    var arr=[];
    var p=ele.parentNode.children;
    for(var i=0;i< p.length;i++){
        if(p[i]!==ele){
            arr.push(p[i]);
        }
    }

    return arr;
}
/**
 *
 * 功能: 传入标签id,获取标签元素对象
 * @param id
 * @returns {Element}
 */
function gid(id){
    return document.getElementById(id);
}
