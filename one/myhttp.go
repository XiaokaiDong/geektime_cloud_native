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
