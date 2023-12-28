/**
 * 将带头的URI转base64
 * @type {string}
 */
const normal= "data:image/;base64,";
const prefixJpeg = "data:image/jpeg;base64,";
const prefixPng = "data:image/png;base64,";
const prefixJpg = "data:image/jpg;base64,";
function uriToB64(b64Str) {
    var result;
    if (b64Str.startsWith(normal)) {
        result = b64Str.substring(normal.length);
    } else if(b64Str.startsWith(prefixJpeg)) {
        result = b64Str.substring(prefixJpeg.length);
    } else if (b64Str.startsWith(prefixPng)){
        result = b64Str.substring(prefixPng.length);
    } else if (b64Str.startsWith(prefixJpg)) {
        result = b64Str.substring(prefixJpg.length);
    } else {
        alert("只允许上传jpeg/png")
        return null;
    }
    return result;
}

/**
 * 将base64转带头的URI
 * @type {string}
 */
function b64ToURI(uri) {
    if (uri && uri.trim() !== "" && uri !== undefined) {
        return prefixPng + uri;
    }
    else return null;
}
/**
 * 将timestamp
 * @param timeStamp
 * @returns {string}
 */
function timeStampToTime(timeStamp) {
    const date = new Date(timeStamp+ 8 * 3600 * 1000);
    const hours = date.getUTCHours().toString().padStart(2, '0');
    const minutes = date.getUTCMinutes().toString().padStart(2, '0');
    const seconds = date.getUTCSeconds().toString().padStart(2, '0');
    return `${hours}:${minutes}:${seconds}`;
}

function timeStampToDateTime(timeStamp) {
    const date = new Date(timeStamp); // 创建 Date 对象，传入时间戳作为参数
    const yyyy = date.getFullYear(); // 获取年份
    const MM = String(date.getMonth() + 1).padStart(2, '0'); // 获取月份，并将单个数字前面补零
    const dd = String(date.getDate()).padStart(2, '0'); // 获取日期，并将单个数字前面补零
    const HH = String(date.getHours()).padStart(2, '0'); // 获取小时，并将单个数字前面补零
    const mm = String(date.getMinutes()).padStart(2, '0'); // 获取分钟，并将单个数字前面补零
    const ss = String(date.getSeconds()).padStart(2, '0'); // 获取秒数，并将单个数字前面补零

    return `${yyyy}-${MM}-${dd} ${HH}:${mm}:${ss}`
}

const levelList = [200, 500, 1000, 2000, 5000, 99999, 1e9];
function expToLevel(exp) {
    if (exp < levelList[0])
        return 0;
    else if (exp < levelList[1])
        return 1;
    else if (exp < levelList[2])
        return 2;
    else if (exp < levelList[3])
        return 3;
    else if (exp < levelList[4])
        return 4;
    else if (exp < levelList[5])
        return 5;
    else
        return 6;
}

function remainExp(exp, level) {
    return exp * 100.0 / levelList[level+1] ;
}