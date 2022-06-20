function fn() {
  var port = karate.properties['karate.server.port'];
  var env = karate.properties['karate.server.env'];
  var ssl = karate.properties['karate.server.ssl'];

  port = port || '8080';
  env = env || 'local';
  if(ssl) {
    karate.configure('ssl', true);
  }
  //karate.configure('connectionTimeout', 15000);
 // karate.configure('readTimeout', 5000);

  if( 'local' == env) {
    return { mockServerURL: (ssl ? 'https' : 'http') + '://localhost:'+ port +'/'}
  }
}