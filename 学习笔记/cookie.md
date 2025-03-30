## cookie
### 1. 定义：客户端技术，将数据保存到客户端，以后每次请求带cookie数据进行访问
### 2. 基本使用：
- 1. 创建Cookie对象，设置数据 
- ```` Cookie cookie=new Cookie("key","value"); ````
- 2. 发送Cookie到客户端，使用response对象
- ```` response.addCookie(cookie);````
- 3. 获取客户端携带的所有Cookie,使用request对象
- ```` Cookie[] cookies=request.getCookies();````
- 4. 遍历数组，获取每一个cookie对象,并使用cookie对象获取数据
- ```` for(Cookies:cookies)
{String name=cookie.getName();
String value=cookie.getValue();
} ````
---
### 3. 原理
- 基于HTTP协议，


### 4. 使用细节
- 默认情况下，存储于浏览器的内存里面，浏览器关闭，cookie销毁
- setMaxAge(int seconds) 设置cookie的存活时间 
- Cookie不支持中文 ， 将中文进行转码，```` String name="吴"
 value=	URLEncoder.encode(value,"UTF-8");
 Sysyem.out.println(value)
 URLDecoder.decode(value,"UTF-8");

