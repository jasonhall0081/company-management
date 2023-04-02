# Appointment Backend
curl -i -X POST \
  --url http://localhost:8001/services/ \
  --data 'name=appointment' \
  --data 'url=http://appointment:8080';

curl -i -X POST \
  --url http://localhost:8001/services/appointment/routes \
  --data 'paths[]=/appointment' \
  --data 'name=appointment-route';

# Hiring Backend
curl -i -X POST \
  --url http://localhost:8001/services/ \
  --data 'name=hiring' \
  --data 'url=http://hiring:8080';

curl -i -X POST \
  --url http://localhost:8001/services/hiring/routes \
  --data 'paths[]=/hiring' \
  --data 'name=hiring-route';

# Document Backend
curl -i -X POST \
  --url http://localhost:8001/services/ \
  --data 'name=document' \
  --data 'url=http://document:8080/v1/document';

curl -i -X POST \
  --url http://localhost:8001/services/document/routes \
  --data 'paths[]=/document' \
  --data 'name=document-route';


# Allow `Connections from Frontend
curl -X POST http://localhost:8001/services/document/plugins \
    --data "name=cors"  \
    --data "config.origins=http://localhost:5173" \
    --data "config.methods=GET" \
    --data "config.methods=POST" \
    --data "config.headers=Accept" \
    --data "config.headers=Accept-Version" \
    --data "config.headers=Content-Length" \
    --data "config.headers=Content-MD5" \
    --data "config.headers=Content-Type" \
    --data "config.headers=Date" \
    --data "config.headers=X-Auth-Token" \
    --data "config.exposed_headers=X-Auth-Token" \
    --data "config.credentials=true" \
    --data "config.max_age=3600";

curl -k -X POST --url http://localhost:8001/plugins \
  --data name=oidc \
  --data config.realm=master \
  --data config.client_id=kong \
  --data config.client_secret=yUbfDBfalE1aNQPuD7zD4gDscNX85Z7e \
  --data config.discovery=http://sso:8080/auth/realms/master/.well-known/openid-configuration;


# SSO Backend
curl -i -X POST \
  --url http://localhost:8001/services/ \
  --data 'name=sso' \
  --data 'url=http://sso:8080/auth';

curl -i -X POST \
  --url http://localhost:8001/services/sso/routes \
  --data 'paths[]=/sso' \
  --data 'name=sso-route';


curl -X OPTIONS http://sso:8080/auth/realms/master/.well-known/openid-configuration -H 'Origin: http://localhost:5173'