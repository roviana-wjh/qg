### Session

1. 定义：服务端会话跟踪技术：将数据保存到服务端·
2. 使用：
- 获取Session对象 ```` HttpSession session=request.getSession();````
- 发送数据 ```` session.setAttribute("","");````
- 获取数据  ```` Object username=session.getAttribute("",""); ```` - 

3. 使用细节
- session的使用是基于Cookie实现的
- 钝化：服务器关闭后，tomcat会自动将Session数据写入硬盘的文件中
- 活化：再次启动服务器后，从文件加载到Session中
- 销毁： 默认情况下，存活时间为30min.调用Session的invalidate方法自动销毁