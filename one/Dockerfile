FROM alpine
ENV VERSION=my_version
LABEL multi.label1="for moudle2" 
COPY bin/amd64/httpserver /httpserver
EXPOSE 8080
ENTRYPOINT ["/httpserver"]
CMD [ "" ]