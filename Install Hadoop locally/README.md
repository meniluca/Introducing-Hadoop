How to install hadoop in few steps
==================================

### Download Hadoop

Choose a version: [http://hadoop.apache.org/releases.html#Download](http://hadoop.apache.org/releases.html#Download)

### Windows:

Beware of what version to install on windows because the last stable presents some problems (see [this](http://stackoverflow.com/questions/10509427/hadoop-in-windows), and [this](https://issues.apache.org/jira/browse/HADOOP-7682)). You can find a patch [here](https://github.com/congainc/patch-hadoop_7682-1.0.x-win).

You can also use older versions like the `0.22.X` or the old legacy stable version `0.20.203.X`.

* [Setup ssh for windows on Cygwin](https://gist.github.com/tariqmislam/2159173/raw/c03008593ec2f6eeb72a973c6912c48e16d61a36/instructions+and+how-to)
* [Cygwin for Hadoop (Win 7)](http://mukulcygwin.blogspot.it/)
* [Cloud9, start standalone](http://lintool.github.io/Cloud9/docs/content/start-standalone.html)
* [Cloud9, start EC2](http://lintool.github.io/Cloud9/docs/content/start-EC2.html)

### Linux

How to install **pseudo-distributed version**:

The following steps are just few hints, is more a guideline than precise directives.

1) Install `java-jdk`

2) Insert `JAVA_HOME` in your `PATH`

3) Create new ssh keys and set passwordless connection with `localhost`

4) Download a stable version of hadoop

5) Unzip in your file system (advised: create a link. Ex.: `ln -s hadoop-1.0.4 hadoop`)

6) Add `HADOOP_HOME` in your `PATH`

7) Uncomment and modify `export JAVA_HOME=...` in `conf/hadoop-env`

8) Add these properties to `conf/core-site.xml` with a valid folder (the folder has to contain `dfs/name/`)

    <property>
    <name>hadoop.tmp.dir</name>
	  <value>/home/USERNAME/hadoop/tmp</value>
	  <description>A base for other temporary directories. 
	  	Ex.: /opt/hadoop/tmp with subfolders dfs/name/</description>
	</property>
	
	<property>
	  <name>fs.default.name</name>
	  <value>hdfs://localhost:54310</value>
	  <description>The name of the default file system.  A URI whose
	  scheme and authority determine the FileSystem implementation.  The
	  uri's scheme determines the config property (fs.SCHEME.impl) naming
	  the FileSystem implementation class.  The uri's authority is used to
	  determine the host, port, etc. for a filesystem.</description>
	</property>
  
9) Add this property to `conf/hdfs-site.xml`

      <property>
	  <name>dfs.replication</name>
	  <value>1</value>
	  <description>Default block replication.
	  The actual number of replications can be specified when the file is created.
	  The default is used if replication is not specified in create time.
	  </description>
	</property>
  
10) Add this property to `conf/mapred-site.xml`
 
  	<property>
	  <name>mapred.job.tracker</name>
	  <value>localhost:54311</value>
	  <description>The host and port that the MapReduce job tracker runs
	  at.  If "local", then jobs are run in-process as a single map
	  and reduce task.
	  </description>
	</property>

11) Format hdfs

12) Now you can start hdfs and mapreduce (`start-dfs.sh` and `start-mapred.sh`)
