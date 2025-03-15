package skupdfNew;

import java.io.File;
import java.nio.file.Paths;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.DownloadFileRequest;
import software.amazon.awssdk.transfer.s3.model.FileDownload;
import software.amazon.awssdk.transfer.s3.progress.LoggingTransferListener;

public class AwsImageDownload {
	
	String awsFile;
	
	public AwsImageDownload() {}
	public AwsImageDownload(String awsFile){
        this.awsFile=awsFile;
    }
	public String getawsFile() {
        return awsFile;
    }
	
	
	private void buildS3Client(String accessKey, String secretKey, Region apSouth1) {
		StaticCredentialsProvider staticCredentialsProvider = StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey));
		S3Client.builder().credentialsProvider(staticCredentialsProvider).region(apSouth1).build();
	}
	
	public String downloadTheFile(String bucketname, String keyname,String imageFile)
	{
		String imageStore = String.format("C:\\Users\\Megha Mathur\\Desktop\\PdfTester\\TestImages\\%s",imageFile);
		
		
		S3TransferManager transferManager = S3TransferManager.create();
		String localDownloadedImage  = imageStore;
        DownloadFileRequest downloadFileRequest =
               DownloadFileRequest.builder()
                                  .getObjectRequest(req -> req.bucket(bucketname).key(keyname))
                                                              .destination(Paths.get(localDownloadedImage))
                                                              .addTransferListener(LoggingTransferListener.create())
                                                              .build();

        FileDownload download = transferManager.downloadFile(downloadFileRequest);

        download.completionFuture().join();
        
        System.out.println("Download Complete !!!!!!");
        
        return localDownloadedImage;
	}
	
	public String DownloadInstructions() { 
		AwsImageDownload act = new AwsImageDownload();
		
		act.buildS3Client(AwsKeys.getAccessKey(), AwsKeys.getSecretKey(), Region.AP_SOUTH_1);
		
		File file = new File(getawsFile());
		String downloadedImageString = act.downloadTheFile("orastore1", "oradata/"+getawsFile(),file.getName());
		
		return downloadedImageString;
    }
}
