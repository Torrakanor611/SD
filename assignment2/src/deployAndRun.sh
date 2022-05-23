xterm  -T "General Repository" -hold -e "./GeneralReposDeployAndRun.sh" &
xterm  -T "Table" -hold -e "./TableDeployAndRun.sh" &
xterm  -T "Kitchen" -hold -e "./KitchenDeployAndRun.sh" &
xterm  -T "Bar" -hold -e "./BarDeployAndRun.sh" &
sleep 1
xterm  -T "Chef" -hold -e "./ChefDeployAndRun.sh" &
xterm  -T "Waiter" -hold -e "./WaiterDeployAndRun.sh" &
xterm  -T "Student" -hold -e "./StudentDeployAndRun.sh" &