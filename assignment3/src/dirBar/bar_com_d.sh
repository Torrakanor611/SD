CODEBASE="http://l040101-ws02.ua.pt/"$1"/classes/"
java -Djava.rmi.server.codebase=$CODEBASE\
     -Djava.rmi.server.useCodebaseOnly=true\
     -Djava.security.policy=java.policy\
     serverSide.main.ServerRestaurantBar 22112 l040101-ws01.ua.pt 22110 l040101-ws04.ua.pt 22113