# syntax=docker/dockerfile:1
FROM python:3.7-alpine
WORKDIR /code
ENV FLASK_APP=app.py
ENV FLASK_RUN_HOST=0.0.0.0
RUN apk add --no-cache gcc musl-dev linux-headers
COPY requirements.txt requirements.txt
RUN pip install -r requirements.txt
#fetch webmethods-integrationserver image
FROM sagcr.azurecr.io/webmethods-integrationserver:10.15
COPY app.py /opt/softwareag/IntegrationServer/instances/default/package
EXPOSE 5000
COPY . .
CMD ["flask", "run"]