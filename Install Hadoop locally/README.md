How to install hadoop
=====================

### Download Hadoop

Choose a version: [http://hadoop.apache.org/releases.html#Download](http://hadoop.apache.org/releases.html#Download)

* Single node setup: [Apache Hadoop - Getting started](http://hadoop.apache.org/docs/stable/single_node_setup.html)

### Windows:

Beware of what version to install on windows because the last stable presents some problems (see [this](http://stackoverflow.com/questions/10509427/hadoop-in-windows), and [this](https://issues.apache.org/jira/browse/HADOOP-7682)). You can find a patch [here](https://github.com/congainc/patch-hadoop_7682-1.0.x-win).

You can also use older versions like the `0.22.X` or the old legacy stable version `0.20.203.X`.

Alternatively you can install any Linux distrubution on [Oracle VM VirtualBox](https://www.virtualbox.org/wiki/Downloads) to work virtually in a safe way.

To use Hadoop on Windows you have to install [Cygwin](http://www.cygwin.com/). During the installation you can select programs to install (in addiction to default programs) and there select ssh (needed for pseudo-distributed mode). Find your `~/.bashrc` file in which you can export the following variable: `JAVA_HOME`, `HADOOP_HOME`, `HADOOP_INSTALL`.

If you want to install hadoop in **standalone mode** you can just stop here.

Here you can find some good tutorial for **standalone mode** and **pseudo-distributed mode**:

* [Setup Cygwin for Hadoop (Win 7)](http://mukulcygwin.blogspot.it/)   ← :D
* [Running hadoop on Windows](http://hayesdavis.net/2008/06/14/running-hadoop-on-windows/)
* [Installing hadoop apache](http://www.oreillynet.com/pub/a/other-programming/excerpts/hadoop-tdg/installing-apache-hadoop.html)
* [Getting started in standalone mode](http://lintool.github.io/Cloud9/docs/content/start-standalone.html)  ← :D
* [hadoop-modes-explained-standalone](http://www.javacodegeeks.com/2012/01/hadoop-modes-explained-standalone.html)
* [Standalone-hadoop](https://github.com/jweese/thrax/wiki/Standalone-hadoop)  ← :D

### Linux

* A useful online guide: [Running hadoop on ubuntu linux single node cluster](http://www.michael-noll.com/tutorials/running-hadoop-on-ubuntu-linux-single-node-cluster/) ← :D:D

How to install **pseudo-distributed mode**:

The following steps are just few hints, is more a guideline than precise directives.

1) Install `java-jdk`

2) Insert `JAVA_HOME` in your `PATH`

3) Create new ssh keys and set passwordless connection with `localhost`
* ssh-keygen
* ssh-copy-id -i .ssh/id_rsa.pub localhost

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

11) Format hdfs: `hadoop namenode -format`

12) Now you can start hdfs and mapreduce (`start-dfs.sh` and `start-mapred.sh`)
