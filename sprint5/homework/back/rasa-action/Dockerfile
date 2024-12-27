FROM python:3.10-slim-bullseye
WORKDIR /rasa
RUN python -m ensurepip --upgrade \
    && apt update \
    && apt upgrade -y \
    && apt -y install rustc cargo \
    && pip3 install setuptools -U \
    && pip3 install tensorflow==2.12.0 \
    && pip3 install rasa==3.6.20 \
    && pip3 install numpy transformers \
    && rasa --version \
    && mkdir -p /rasa/actions
COPY ./actions /rasa/actions
EXPOSE 5055
ENTRYPOINT [ "/usr/local/bin/rasa", "run", "actions", "-vv" ]
