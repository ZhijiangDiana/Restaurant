/**
 * 将base64转带头的URI
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

function b64ToURI(uri) {
    return prefixPng + uri;
}