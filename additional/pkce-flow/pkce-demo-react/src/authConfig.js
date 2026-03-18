export const authConfig = {
    clientId: 'oauth2-pkce-demo',
    authorizationEndpoint: 'http://127.0.0.1:8444/realms/oauth2-demos/protocol/openid-connect/auth',
    tokenEndpoint: 'http://127.0.0.1:8444/realms/oauth2-demos/protocol/openid-connect/token',
    redirectUri: 'http://localhost:5173',
    scopes: 'openid profile email offline_access',
    onRefreshTokenExpire: (event) =>  event.logIn()
};