
Konfiguration für Elasticsearch
-------------------------------

https://www.elastic.co/guide/en/elasticsearch/guide/current/_file_descriptors_and_mmap.html

Elasticsearch also uses a mix of NioFS and MMapFS for the various files. Ensure that you configure the maximum map count so that there is ample virtual memory available for mmapped files. This can be set temporarily:

````
sysctl -w vm.max_map_count=262144
````

Or you can set it permanently by modifying vm.max_map_count setting in your /etc/sysctl.conf.
