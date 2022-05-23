echo "Transfering data to the chef node."
sshpass -f password ssh sd102@l040101-ws01.ua.pt 'mkdir -p Restaurant'
sshpass -f password ssh sd102@l040101-ws01.ua.pt 'rm -rf Restaurant/*'
echo "Test Successfull"