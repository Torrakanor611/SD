xterm  -T "RMI registry" -hold -e "./RMIRegistryDeployAndRun.sh" &
sleep 4
xterm  -T "Registry" -hold -e "./RegistryDeployAndRun.sh" &
sleep 4
xterm  -T "General Repository" -hold -e "./GeneralReposDeployAndRun.sh" &
sleep 2
xterm  -T "Kitchen" -hold -e "./KitchenDeployAndRun.sh" &
sleep 2
xterm  -T "Table" -hold -e "./TableDeployAndRun.sh" &
sleep 4
xterm  -T "Bar" -hold -e "./BarShopDeployAndRun.sh" &
sleep 1
xterm  -T "Chef" -hold -e "./ChefDeployAndRun.sh" &
xterm  -T "Waiter" -hold -e "./WaiterDeployAndRun.sh" &
xterm  -T "Student" -hold -e "./StudentDeployAndRun.sh" &