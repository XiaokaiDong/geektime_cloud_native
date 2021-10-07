# 极客时间云原生训练营第一次作业

> 作业内容
>
> 编写一个 HTTP 服务器，大家视个人不同情况决定完成到哪个环节，但尽量把1都做完
>
> 
>
> 1.接收客户端 request，并将 request 中带的 header 写入 response header
>
> 2.读取当前系统的环境变量中的 VERSION 配置，并写入 response header
>
> 3.Server 端记录访问日志包括客户端 IP，HTTP 返回码，输出到 server 端的标准输出
>
> 4.当访问 localhost/healthz 时，应返回200



## 作业内容

基于老师课上的实例代码httpserver修改而来

1. 前三题

```go
func rootHandler(w http.ResponseWriter, r *http.Request) {
	fmt.Println("entering root handler")
	//1. 写响应头必须在放在写响应码（w.WriteHeader）和写实际内容（w.Write）之前
	for k, v := range r.Header {
		for _, subV := range v {
			w.Header().Set(k, subV)
		}

	}

	statusCode := 200

	w.WriteHeader(statusCode)

	//2. 获取环境变量VERSION的值
	io.WriteString(w, fmt.Sprintf("The VERSION is [%s]\n", os.Getenv("VERSION")))
	//获取远端IP地址
	io.WriteString(w, fmt.Sprintf("Hello [%s]\n", r.RemoteAddr))

    //3. Server 端记录访问日志包括客户端 IP，HTTP 返回码，输出到 server 端的标准输出
	fmt.Printf("response [%s] with http response code [%d]\n", r.RemoteAddr, statusCode)
}
```



2. 第2题

   ```go
   func healthz(w http.ResponseWriter, r *http.Request) {
   	w.WriteHeader(200)
   	io.WriteString(w, "ok\n")
   }
   ```

3. 整体代码如下：

   ```go
   package main
   
   import (
   	"fmt"
   	"io"
   	"log"
   	"net/http"
   	"os"
   
   	"github.com/golang/glog"
   )
   
   func main() {
   	glog.V(2).Info("Starting http server...")
   
   	http.HandleFunc("/", rootHandler)
   	http.HandleFunc("/healthz", healthz)
   
   	log.Fatal(http.ListenAndServe(":8080", nil))
   }
   
   func healthz(w http.ResponseWriter, r *http.Request) {
   	w.WriteHeader(200)
   	io.WriteString(w, "ok\n")
   }
   
   func rootHandler(w http.ResponseWriter, r *http.Request) {
   	fmt.Println("entering root handler")
   	//写响应头必须在放在写响应码（w.WriteHeader）和写实际内容（w.Write）之前
   	for k, v := range r.Header {
   		for _, subV := range v {
   			w.Header().Set(k, subV)
   		}
   
   	}
   
   	statusCode := 200
   
   	w.WriteHeader(statusCode)
   
   	//获取环境变量VERSION的值
   	io.WriteString(w, fmt.Sprintf("The VERSION is [%s]\n", os.Getenv("VERSION")))
   	//获取远端IP地址
   	io.WriteString(w, fmt.Sprintf("Hello [%s]\n", r.RemoteAddr))
   
   	fmt.Printf("response [%s] with http response code [%d]\n", r.RemoteAddr, statusCode)
   }
   
   ```

   