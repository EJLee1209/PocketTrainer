package com.dldmswo1209.pockettrainer.datas

data class ExerciseItem(
    var name: String,
    var target: String,
    var isSelected: Boolean
)

data class UserInfo(
    val uid: String,
    var id: String,
    var pw: String,
    var nickname: String
)

// 가슴운동 리스트
val chestExerciseList by lazy {
    mutableListOf(
        ExerciseItem("덤벨 로우 플라이","가슴", false),
        ExerciseItem("덤벨 체스트 플라이","가슴",false),
        ExerciseItem("덤벨 풀오버","가슴",false),
        ExerciseItem("덤벨 해머 프레스","가슴",false),
        ExerciseItem("덤벨 프레스","가슴",false),
        ExerciseItem("디클라인 덤벨 프레스","가슴",false),
        ExerciseItem("디클라인 벤치 프레스","가슴",false),
        ExerciseItem("디클라인 체스트 프레스","가슴",false),
        ExerciseItem("디클라인 푸시업","가슴",false),
        ExerciseItem("딥스(가슴)","가슴",false),
        ExerciseItem("머신 딥스(가슴)","가슴",false),
        ExerciseItem("버터플라이","가슴",false),
        ExerciseItem("벤치프레스","가슴",false),
        ExerciseItem("스미스 머신 벤치프레스","가슴",false),
        ExerciseItem("어시스티드 딥스(가슴)","가슴",false),
        ExerciseItem("인클라인 덤벨 체스트 플라이","가슴",false),
        ExerciseItem("인클라인 덤벨 해머 프레스","가슴",false),
        ExerciseItem("인클라인 덤벨 프레스","가슴",false),
        ExerciseItem("인클라인 벤치 프레스","가슴",false),
        ExerciseItem("인클라인 체스트 프레스","가슴",false),
        ExerciseItem("인클라인 푸시업","가슴",false),
        ExerciseItem("체스트 프레스","가슴",false),
        ExerciseItem("케이블 크로스 오버","가슴",false),
        ExerciseItem("펙 덱 플라이","가슴",false),
        ExerciseItem("푸시업","가슴",false),
        ExerciseItem("플레이트 프레스","가슴",false)
    )
}

val backExerciseList by lazy {
    mutableListOf(
        ExerciseItem("풀업","등",false),
        ExerciseItem("덤벨 풀오버(등)","등",false),
        ExerciseItem("랙풀 데드리프트","등",false),
        ExerciseItem("랫풀다운","등",false),
        ExerciseItem("루마니안 데드리프트","등",false),
        ExerciseItem("바벨로우","등",false),
        ExerciseItem("백 익스텐션","등",false),
        ExerciseItem("슈퍼맨","등",false),
        ExerciseItem("시티드 로우","등",false),
        ExerciseItem("암풀다운","등",false),
        ExerciseItem("친업","등",false),
        ExerciseItem("언더 그립 바벨로우","등",false),
        ExerciseItem("원 암 케이블 로우","등",false),
        ExerciseItem("원 암 덤벨 로우","등",false),
        ExerciseItem("인버티드 로우","등",false),
        ExerciseItem("케이블 로우","등",false),
        ExerciseItem("케이블 시티드 로우","등",false),
        ExerciseItem("케이블 시티드 원 암 로우","등",false),
        ExerciseItem("티바로우","등",false)
    )
}

val legExerciseList by lazy {
    mutableListOf(
        ExerciseItem("스쿼트","하체",false),
        ExerciseItem("덤벨 런지","하체",false),
        ExerciseItem("덤벨 스쿼트","하체",false),
        ExerciseItem("덤벨 워킹 런지","하체",false),
        ExerciseItem("레그 컬","하체",false),
        ExerciseItem("레그 익스텐션","하체",false),
        ExerciseItem("레그 프레스","하체",false),
        ExerciseItem("로우바 스쿼트","하체",false),
        ExerciseItem("머신 레그프레스","하체",false),
        ExerciseItem("바벨 런지","하체",false),
        ExerciseItem("바벨 워킹 런지","하체",false),
        ExerciseItem("스모 데드리프트","하체",false),
        ExerciseItem("스미스 런지","하체",false),
        ExerciseItem("스미스 머신 스쿼트","하체",false),
        ExerciseItem("스탠딩 레그 컬","하체",false),
        ExerciseItem("스티프 데드리프트","하체",false),
        ExerciseItem("점프 스쿼트","하체",false),
        ExerciseItem("컨벤셔널 데드리프트","하체",false),
        ExerciseItem("하이바 스쿼트","하체",false),
        ExerciseItem("핵 스쿼트","하체",false),
        ExerciseItem("브이 스쿼트","하체",false)
    )
}

val shoulderExerciseList by lazy {
    mutableListOf(
        ExerciseItem("사이드 레터럴 레이즈","어깨",false),
        ExerciseItem("T-레이즈","어깨",false),
        ExerciseItem("Y-레이즈","어깨",false),
        ExerciseItem("덤벨 레터럴 레이즈","어깨",false),
        ExerciseItem("덤벨 벤트오버 레터럴 레이즈","어깨",false),
        ExerciseItem("덤벨 숄더 프레스","어깨",false),
        ExerciseItem("덤벨 업 라잇 로우","어깨",false),
        ExerciseItem("덤벨 프론트 레이즈","어깨",false),
        ExerciseItem("리닝 덤벨 레터럴 레이즈","어깨",false),
        ExerciseItem("리닝 케이블 레터럴 레이즈","어깨",false),
        ExerciseItem("머신 레터럴 레이즈","어깨",false),
        ExerciseItem("머신 숄더 프레스","어깨",false),
        ExerciseItem("밀리터리 프레스","어깨",false),
        ExerciseItem("바벨 업 라잇 로우","어깨",false),
        ExerciseItem("바벨 프론트 레이즈","어깨",false),
        ExerciseItem("비하인드 넥 프레스","어깨",false),
        ExerciseItem("스미스 비하인드 넥 프레스","어깨",false),
        ExerciseItem("시티드 바벨 숄더 프레스","어깨",false),
        ExerciseItem("시티드 스미스 숄더 프레스","어깨",false),
        ExerciseItem("아놀드 프레스","어깨",false),
        ExerciseItem("케이블 레터럴 레이즈","어깨",false),
        ExerciseItem("케이블 벤트오버 레이즈","어깨",false),
        ExerciseItem("케이블 프론트 레이즈","어깨",false),
        ExerciseItem("페이스풀","어깨",false),
        ExerciseItem("펙덱 리어델트 레이즈","어깨",false),
    )
}

val bicepsExerciseList by lazy {
    mutableListOf(
        ExerciseItem("덤벨 컬","이두",false),
        ExerciseItem("바벨 컬","이두",false),
        ExerciseItem("리스트 컬","이두",false),
        ExerciseItem("케이블 컬","이두",false),
        ExerciseItem("프리처 컬","이두",false),
        ExerciseItem("해머 컬","이두",false)
    )
}

val tricepsExerciseList by lazy {
    mutableListOf(
        ExerciseItem("내로우 푸시업","삼두",false),
        ExerciseItem("덤벨 오버헤드 익스텐션","삼두",false),
        ExerciseItem("덤벨 킥백","삼두",false),
        ExerciseItem("딥스(삼두)","삼두",false),
        ExerciseItem("라잉 트라이셉스 익스텐션","삼두",false),
        ExerciseItem("머신 딥스(삼두)","삼두",false),
        ExerciseItem("머신 트라이셉스 익스텐션","삼두",false),
        ExerciseItem("벤치 딥스","삼두",false),
        ExerciseItem("시티드 덤벨 오버헤드 익스텐션","삼두",false),
        ExerciseItem("시티드 트라이셉스 익스텐션","삼두",false),
        ExerciseItem("케이블 킥백","삼두",false),
        ExerciseItem("케이블 트라이셉 익스텐션","삼두",false),
        ExerciseItem("케이블 푸시 다운","삼두",false),
        ExerciseItem("클로즈 그립 벤치 프레스","삼두",false)
    )
}

val trapeziusExerciseList by lazy {
    mutableListOf(
        ExerciseItem("덤벨 슈러그","승모근",false),
        ExerciseItem("바벨 슈러그","승모근",false)
    )
}

val absExerciseList by lazy {
    mutableListOf(
        ExerciseItem("데드버그","복근",false),
        ExerciseItem("라잉 레그레이즈","복근",false),
        ExerciseItem("리버스 크런치","복근",false),
        ExerciseItem("버드독","복근",false),
        ExerciseItem("사이드 밴드","복근",false),
        ExerciseItem("싯업","복근",false),
        ExerciseItem("케이블 크런치","복근",false),
        ExerciseItem("크런치","복근",false),
        ExerciseItem("토 터치 크런치","복근",false),
        ExerciseItem("트위스트 크런치","복근",false),
        ExerciseItem("행잉 레그레이즈","복근",false)
    )
}

val aerobicExerciseList by lazy {
    mutableListOf(
        ExerciseItem("런닝","유산소",false),
        ExerciseItem("버피","유산소",false),
        ExerciseItem("사이드 플랭크","유산소",false),
        ExerciseItem("싸이클","유산소",false),
        ExerciseItem("인터벌 싸이클","유산소",false),
        ExerciseItem("일립티컬 머신","유산소",false),
        ExerciseItem("트레드 밀","유산소",false),
        ExerciseItem("플랭크","유산소",false),
        ExerciseItem("할로우 포지션","유산소",false
        )
    )
}
