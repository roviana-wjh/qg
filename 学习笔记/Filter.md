##  Filter

1. 概念：将对资源的请求拦截下来
2. 一般完成一些通用的操作，比如权限的控制，统一编码处理
---

### 快速入门
1. 定义类，实现接口，重写所有方法
2. 配置拦截资源的路径：用@WebFilter注解
3. 在dofilter方法中输出一句话，并放行 ````chain.dofilter(req,resp)````
4. 执行放行前的逻辑 ->放行 ->访问资源 ->执行放行后的逻辑
5. 放行前通常退req对象进行处理，放行后对reap对象进行处理

---
### 使用细节
- 1. 拦截具体资源
- 2. 拦截目录的资源
- 3. 后缀名拦截
- 4. 拦截所有访问资源
- 5. 过滤器链 ：配置多个过滤器，执行顺序按自然排序来来执行
