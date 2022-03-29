import java.nio.file.Path;
import java.util.concurrent.Callable;

public record WatchCallable(Path path) implements Callable<Void> {

    @Override
    public Void call() {
        DirectoryObserver dirObserver = DirectoryObserver.getDirectoryObserverInstance();
        dirObserver.initializeWatcher(path);
        dirObserver.createProcessedDir(path);

        // ensure processed directory is created first before watching for files
        try {
            Thread.sleep(15000);
            System.out.println("Done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        dirObserver.startFileWatch(path);
        return null;
    }
}