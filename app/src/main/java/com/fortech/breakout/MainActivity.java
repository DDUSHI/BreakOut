package com.fortech.breakout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private int lastOpenedStage = 1; // 가장 마지막에 열린 스테이지 번호를 저장하는 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 게임 시작 버튼
        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 가장 마지막에 열린 스테이지 실행
                startGame(lastOpenedStage);
            }
        });

        // 스테이지 설정 버튼
        Button stageButton = findViewById(R.id.stageButton);
        stageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 스테이지 설정 화면으로 이동
                openStageSelection();
            }
        });

        // 도움말 버튼
        Button helpButton = findViewById(R.id.helpButton);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 도움말 화면으로 이동
                openHelpScreen();
            }
        });
    }

    private void startGame(int stage) {
        // 게임 실행 로직 구현
        // 선택한 스테이지 번호를 기반으로 게임을 시작하는 코드 작성
    }

    private void openStageSelection() {
        // 스테이지 설정 화면으로 이동하는 로직 구현
        // Intent를 사용하여 StageSelectionActivity로 전환하는 코드 작성
    }

    private void openHelpScreen() {
        // 도움말 화면으로 이동하는 로직 구현
        // Intent를 사용하여 HelpActivity로 전환하는 코드 작성
    }
}
