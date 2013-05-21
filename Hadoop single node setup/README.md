Hadoop single node setup
=====================
*(how to install Hadoop in pseudo-distributed mode)*

There are three supported mode to install Hadoop:
* Local (Standalone) Mode
* Pseudo-Distributed Mode
* Fully-Distributed Mode

We choose to install the pseudo-distributed, because is really close to the real distributed mode.

### Downloading Hadoop

Choose a version: [http://hadoop.apache.org/releases.html#Download](http://hadoop.apache.org/releases.html#Download)

### Windows

We suggest to install any Linux distribution on [Oracle VM VirtualBox](https://www.virtualbox.org/wiki/Downloads) to work virtually in a safe way.

### Linux

The following steps are just few hints, is more a guideline than precise directives.

1) Install *Java Development Kit*.

2) Create a new environment variable called `JAVA_HOME` in your `PATH` variable.

3) Install *ssh* and create new keys, then enable passwordless connection with `localhost`, so:
* ssh-keygen (don't setup passphrases)
* ssh-copy-id -i .ssh/id_rsa.pub localhost

4) Download a stable version of hadoop (better if `0.X` or `1.X`).

5) Unzip in your file system (create a link. Ex.: `ln -s hadoop-X.X.X hadoop`)

6) Add in your `PATH` variable `HADOOP_HOME` with the path of the link above.

7) Uncomment and modify `export JAVA_HOME=...` in `conf/hadoop-env.sh`.

8) Create a folder for temporary files, for instance called `tmp`. Add these properties to `conf/core-site.xml` with the right path of the `tmp` folder (this folder has to contain this other two folders: `dfs/name/`).

    <property>
    <name>hadoop.tmp.dir</name>
	  <value>......./tmp</value>
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

11) Format hdfs with this command `hadoop namenode -format`

12) Now you can start hdfs and mapreduce (`start-dfs.sh` and `start-mapred.sh`)

## References

* A useful online guide: [Running Hadoop on ubuntu linux single node cluster](http://www.michael-noll.com/tutorials/running-hadoop-on-ubuntu-linux-single-node-cluster/)
* How to set up and configure a single-node Hadoop installation: [Apache Hadoop - Single Node Setup](http://hadoop.apache.org/docs/stable/hadoop-project-dist/hadoop-common/SingleNodeSetup.html)
* Instructions primarily for the 0.2x series of Hadoop: [Hadoop Wiki - Getting Started With Hadoop](http://wiki.apache.org/hadoop/GettingStartedWithHadoop)
