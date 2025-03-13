package br.com.pedro.credito.services;

import br.com.pedro.credito.models.dto.CreditoDto;
import br.com.pedro.credito.models.dto.LogDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class LogService {

    private final KafkaTemplate<String, LogDto> kafkaTemplateOrder;
    private final Random random = new Random();

    @SuppressWarnings("null")
    public void sendMessageLog(String message) {
        int partition = random.nextInt(2);
        LogDto log = getLogDto(message);
        kafkaTemplateOrder.send("credito-log", partition, null, log);
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "credito-log", partitions = { "0" }), containerFactory = "orderKafkaListenerContainerFactory")
    public void orderListener(LogDto log) {
        String message = String.format("Mensagem recebida no consumer 01: %s", log.toString());
        System.out.println(message);
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "credito-log", partitions = { "1" }), containerFactory = "orderKafkaListenerContainerFactory")
    public void orderListenerTwo(LogDto log) {
        String message = String.format("Mensagem recebida no consumer 02: %s", log.toString());
        System.out.println(message);
    }

    private LogDto getLogDto(String message) {
        return new LogDto(UUID.randomUUID().toString(), message, LocalDate.now());
    }


}
