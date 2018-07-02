FROM rabbitmq:3.6.14-management-alpine

COPY rabbitmq.conf /etc/rabbitmq/
COPY definitions.json /etc/rabbitmq/
EXPOSE 5672
EXPOSE 15672
CMD ["rabbitmq-server"]