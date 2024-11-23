if [ "$#" -ne 1 ]; then
    echo "Port: $0 <port>"
    echo "Port used by dapr: $1 <dapr_port>"
    echo "Jar URL: $2 <jar_file>"
    exit 1
fi

$port=$0
$dapr_port=$1
$jar_file=$2

dapr run --app-id categories --app-port $port --dapr-http-port $dapr_port -- java -jar $jar_file