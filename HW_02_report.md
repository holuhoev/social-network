До индекса
```
a17636999@CAB-WSM-0007463 bin % ./run_wrk_1
Running 30s test @ http://127.0.0.1:8080
  1 threads and 1 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     1.25s    80.67ms   1.49s    87.50%
    Req/Sec     0.00      0.00     0.00    100.00%
  24 requests in 30.01s, 8.11KB read
Requests/sec:      0.80
Transfer/sec:     276.68B

a17636999@CAB-WSM-0007463 bin % ./run_wrk_10
Running 30s test @ http://127.0.0.1:8080
  2 threads and 10 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     5.39s   243.95ms   5.65s    76.00%
    Req/Sec     9.18     12.99    38.00     82.35%
  50 requests in 30.10s, 16.89KB read
Requests/sec:      1.66
Transfer/sec:     574.73B


a17636999@CAB-WSM-0007463 bin % ./run_wrk_100
Running 30s test @ http://127.0.0.1:8080
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    19.58s     9.21s   30.09s    41.67%
    Req/Sec     9.88     17.93    70.00     85.29%
  72 requests in 30.10s, 200.72KB read
  Non-2xx or 3xx responses: 12
Requests/sec:      2.39
Transfer/sec:      6.67KB

Running 30s test @ http://127.0.0.1:8080
  6 threads and 1000 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    27.42s     2.46s   29.49s    58.82%
    Req/Sec     4.33     10.07    30.00     88.89%
  17 requests in 30.10s, 5.74KB read
  Socket errors: connect 0, read 1196, write 0, timeout 0
Requests/sec:      0.56
Transfer/sec:     195.44B

```

После индекса
```mysql
CREATE INDEX I_user_ON_last_name_first_name ON users(last_name, first_name);
```

```
a17636999@CAB-WSM-0007463 bin % ./run_wrk_1
Running 30s test @ http://127.0.0.1:8080
  1 threads and 1 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     1.82ms    6.07ms 150.67ms   99.47%
    Req/Sec   690.89    103.83     0.88k    75.92%
  20574 requests in 30.02s, 7.10MB read
Requests/sec:    685.39
Transfer/sec:    242.12KB


a17636999@CAB-WSM-0007463 bin % ./run_wrk_10  
Running 30s test @ http://127.0.0.1:8080
  6 threads and 10 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     3.26ms    2.94ms 114.04ms   94.16%
    Req/Sec   320.54     49.13   500.00     68.50%
  57493 requests in 30.03s, 19.64MB read
Requests/sec:   1914.35
Transfer/sec:    669.61KB

a17636999@CAB-WSM-0007463 bin % ./run_wrk_100 
Running 30s test @ http://127.0.0.1:8080
  6 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    51.67ms   14.50ms 287.02ms   85.09%
    Req/Sec   310.94     58.14   460.00     72.11%
  55793 requests in 30.05s, 19.13MB read
Requests/sec:   1856.48
Transfer/sec:    651.83KB

a17636999@CAB-WSM-0007463 bin % ./run_wrk_1000
Running 30s test @ http://127.0.0.1:8080
  6 threads and 1000 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   398.21ms  141.55ms 760.77ms   76.79%
    Req/Sec   329.26     55.10   494.00     71.18%
  58927 requests in 30.10s, 20.55MB read
  Socket errors: connect 0, read 1048, write 0, timeout 0
Requests/sec:   1957.53
Transfer/sec:    699.09KB

```