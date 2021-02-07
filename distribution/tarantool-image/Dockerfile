FROM tarantool/tarantool:1.10.9
COPY ./scripts/app.lua /opt/tarantool/app.lua
ENTRYPOINT ["docker-entrypoint.sh"]

HEALTHCHECK CMD tarantool_is_upx

EXPOSE 3301
CMD [ "tarantool" ]
