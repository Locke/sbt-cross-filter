import cats.effect.{IO, IOApp}
import org.http4s.ember.client.EmberClientBuilder
import org.http4s.client.Client
import org.typelevel.log4cats.LoggerFactory
import org.typelevel.log4cats.slf4j.Slf4jFactory

object Hello extends IOApp.Simple {
  
  private implicit val loggerFactory: LoggerFactory[IO] =
    Slf4jFactory.create[IO]

  def printHello(client: Client[IO]): IO[Unit] =
    client
      .expect[String]("http://localhost:8080/hello/Ember")
      .flatMap(IO.println)

  val run: IO[Unit] = EmberClientBuilder
    .default[IO]
    .build
    .use(client => printHello(client))

}
