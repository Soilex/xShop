package com.github.soilex.xshop.data.mongo;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("spring.data.mongodb")
public class MongoSettingsProperties {

    private String uri = "mongodb://localhost/test";

    /**
     * 每个主机的最小连接数，这些连接在空闲时将保持在连接池中。
     * 默认为 0，不能小于 0
     */
    private Integer minConnectionsPerHost = 0;

    /**
     * 每个主机允许的最大连接数，这些连接在空闲时将保持在连接池中。当连接池耗尽后，任何需要连接的操作都将被阻塞并等待可用连接。
     * 默认为 100，不能小于 1
     */
    private Integer maxConnectionsPerHost = 100;

    /**
     * 允许阻塞的连接线程数乘数。该值和 maxConnectionsPerHost 相乘的结果就是连接等待队列的最大值，超出的线程将立即抛出异常。
     * 如该值为 5，maxConnectionsPerHost 为 10，则最多可以同时有 50 个线程等待连接。
     * 默认为 5，不能小于 1
     */
    private Integer threadsAllowedToBlockForConnectionMultiplier = 5;

    /**
     * 线程从连接池中获取可用连接的最长等待时间（毫秒）。
     * 默认为 120,000（120秒），0 表示不等待，负值意味着无限期等待。
     */
    private Integer maxWaitTime = 120000;

    /**
     * 连接池中连接的最大空闲时间（毫秒）。超出空闲时间的连接将被关闭，并在必要时由新建连接替换。
     * 默认为 0，表示无限制，不能小于 0。
     */
    private Integer maxConnectionIdleTime = 0;

    /**
     * 连接池中连接的最大使用寿命（毫秒）。超出使用寿命的连接将被关闭，并在必要时由新建连接替换。
     * 默认为 0，表示无限制，不能小于 0
     */
    private Integer maxConnectionLifeTime = 0;

    /**
     * 连接超时时间（毫秒），仅在新建连接时使用。
     * 默认为 10,000（10秒），0 表示无限制，不能小于 0。
     */
    private Integer connectTimeout = 10000;

    /**
     * socket 超时时间（毫秒），用于 I/O 读写操作。
     * 默认为 0，表示无限制。
     */
    private Integer socketTimeout = 0;

    /**
     * 是否启用 SSL。
     * 在未设置 socket factory 的情况下，设置该选项时将同时设置默认的 socket factory，true 时为 java.net.ssl.SSLSocketFactory.getDefault()，false 时为 javax.net.SocketFactory.getDefault()。
     * 开启该选项时，如果设置其它 socket factory，则该 factory 必须创建 java.net.ssl.SSLSocket 的实例，否则将拒绝连接。
     * 默认为 false，不启用
     */
    private Boolean sslEnabled = false;

    /**
     * 启用 SSL 时是否允许无效的主机名（证书域名检查）。true 为允许，即关闭域名检查。
     * 设为 true（允许无效主机名）将使应用程序容易受到中间人攻击。
     * 注意，证书域名检查需要 Java 7 及以上版本，如果应用程序使用了 SSL 运行在 Java 6 上，则必须将该选项设为 true（关闭域名检查）。
     * 默认为 false，即开启域名检查，不允许无效的主机名
     */
    private Boolean sslInvalidHostNameAllowed = false;

    /**
     * 设置驱动程序注册的 JMX Beans 是否始终为 MBeans，无论 VM 是不是 Java 6 及更高版本。
     * 当该选项为 false 时，驱动程序将在 Java 6 或更高版本时使用 MXBeans；Java 5 中使用 MBeans。
     * 默认为 false。
     */
    private Boolean alwaysUseMBeans = false;

    /**
     * 心跳检测频率（毫秒）。该选项用于设定驱动程序每次尝试确定每个服务器当前状态的间隔时间。
     * 默认为 10,000（10秒）
     */
    private Integer heartbeatFrequency = 10000;

    /**
     * 心跳检测的最小频率（毫秒）。如果驱动程序需要经常检查服务器的可用性，那么距离上次检测至少等待这么长时间，以避免资源浪费。
     * 默认为 500
     */
    private Integer minHeartbeatFrequency = 500;

    /**
     * 用于心跳检测的连接超时时间（毫秒）。
     * 默认为 20,000（20秒）
     */
    private Integer heartbeatConnectTimeout = 20000;

    /**
     * 用于心跳检测的 socket 超时时间（毫秒）。
     * 默认为 20,000（20秒）
     */
    private Integer heartbeatSocketTimeout = 20000;

    /**
     * 如果对某个操作而言，存在多个合适的服务器，我们可以用localThresholdMS变量来确定一个基于延迟时间（RTT） 的可接受的”延迟窗口范围”(Latency Window)。
     * 以延迟最小的服务器作为基准，所有的服务器如其延迟时间和最小延迟时间的差值小于这个变量定义值，则这些服务器都可以有资格被随机的选中。
     * 如果变量设为0，则不使用随机算法，而是选择延迟时间最小的服务器。
     * 默认值是15毫秒，意味着有资格的服务器的延迟时间只能有比较微小（15ms）的不同
     */
    private Integer localThreshold = 15;

    /**
     * 设定当由于网络错误而写入失败时，是否进行重试
     */
    private boolean retryWrites;

    /**
     * 服务器选择的超时时间（毫秒），指定驱动程序从集群中选择服务器时，无法成功而放弃并抛出异常的时间。可以根据需要（如耐心等待或快速返回错误）来设置该参数的值。
     * 默认为 30,000（30秒），这个时间对于在经典的故障恢复阶段中选举出新的主节点来说已经足够。0 表示无可用服务器，将立即超时；负值意味着无限期等待。
     */
    private int serverSelectionTimeout = 30000;

    /**
     * 写入策略，用于控制写入安全的级别，以保障写操作的可靠性。可选配置如下——
     *
     * w – 该选项要求写入确认操作已经传递到指定数量的节点或指定标签的节点。有以下可选值——
     * w:0 (Unacknowledged) – 写入操作调用后立刻返回，无法知道写入是否成功。性能最好，但可靠性最差，不推荐使用。此外还有一个 w:-1 (error ignored) 级别，基本和 w:0 差不多，不同的是 w:0 对于网络错误会返回异常，而 w:-1 不会捕获任何异常。
     * w:1 (Acknowledged) – 等待实际写入操作完成后才返回响应，如果写入发生错误，可以捕获到并进行处理。该级别具备基本的可靠性，是目前的默认设置。
     * w:2 (Replica Acknowledged) – 当 secondary 节点从 primary 节点完成了复制之后返回响应。其中的数字 2 表示完成复制操作的节点数目，可以是其它 >=2 的数字，如 w:3 表示至少要有 3 个节点有数据。
     * w:majority – 和 w:2 一样，secondary 节点从 primary 节点完成数据复制后返回响应。不同的是这里并不指定具体的节点数量，而是使用 majority 表示“大多数”，即超过一半（>1/2）的节点数。
     * tag set – 指定副本集成员的标签，要求写入确认已经传递到指定 tag 标记的副本集成员后返回响应。
     * wtimeout – 写入超时时间（毫秒）。用于设定一个时间限制，当写入过程持续超过该时间后则认为写入失败。该选项仅适用于集群环境，当指定 w 值大于 1 时生效。数据需要写入指定数量的节点才算成功，但如果有节点发生故障，可能导致这个数量无法达到，从而无法进行响应，通过设定这个超时时间，可以防止写入操作被无限制阻塞。该参数可以设置为 0，表示无限期。
     * journal – 写入操作记录到 Journal Log 持久化之后才向客户端返回响应。该选项要求服务端开启 journaling 功能，不能与 fsync 结合使用。要记录到 Journal Log 必须等到下一次提交，可以通过增加 Journal Log 的提交频率来降低延迟。
     * 默认为 w:1（com.mongodb.WriteConcern.ACKNOWLEDGED）
     */
    private WriteConcern writeConcern = WriteConcern.W1;

    /**
     * 读取策略（隔离级别），决定集群环境中如何返回数据——
     *
     * local – 直接从本地读取，不考虑数据是否已经在集群的其它节点同步，默认值。
     * majority – 只读取成功写入大多数节点的数据。
     * linearizable – Server Version: 3.4 版本引入。和 majority 类似，但修复了 majority 的一些 bug，但比 majority 在性能上的损耗更大。linearizable 对于同一文档的并发读、写操作时线性的。
     * snapshot – Server Version: 4.0 版本引入。MongoDB 从 4.0 开始支持副本集多文档事务，同时提供了 snapshot 隔离级别，在事务开始时创建一个 WiredTiger snapshot（快照），保存当时整个引擎所有事务的状态，确定哪些事务对当前可见，哪些不可见，然后在整个事务过程中使用这个快照提供事务读。
     * 该选项可用于解决“脏读”的问题，如从 primary 节点上读取了某条数据，但这条数据并没有同步到大多数节点，然后 primary 节点故障，重新恢复后 primary 节点会将未同步到大多数节点的数据回滚掉，导致之前读取的数据成了“脏数据”。
     *
     * 而将 readConcern 的级别设置为 majority，则可以保证读取到的数据已经写入大多数节点，这些数据不会发生回滚，也就避免了“脏读”的问题。需要注意的是，该级别能保证读取到的数据不会发生回滚，但并不能保证读到的数据是最新的
     */
    private ReadConcern readConcern = ReadConcern.LOCAL;

    /**
     * 用于查询、Map-Reduce、聚合、计数的读取首选项。
     *
     * MongoDB 有 5 种 ReadPreference 模式——
     *
     * primary – 主节点，默认模式，只从 primary 节点读取，如果 primary 节点不可用，则报错或抛出异常。
     * primaryPreferred – 首选主节点，优先从 primary 节点读取，如果 primary 节点不可用（如故障转移），则从 secondary 节点读取。
     * secondary – 从节点，只从 secondary 节点读取，如果 secondary 节点不可用，则报错或抛出异常。
     * secondaryPreferred – 优先从 secondary 节点读取，如果没有可用的 secondary 节点，则从 primary 节点读取。
     * nearest – 最近节点，根据网络距离从最近的节点读取，可能是 primary 节点或 secondary 节点。
     * 默认为 primary（com.mongodb.ReadPreference.primary()）
     */
    private ReadPreference readPreference = ReadPreference.primary();
}
