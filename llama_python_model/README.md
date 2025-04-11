# LLaMA 3.1 Modeli

## Gereksinimler
- Python 3.x
- Ollama

## Kurulum
```
pip install -r requirements.txt
ollama pull llama3:8b
```

## Çalıştırma
```
python app.py
```

## Kullanım
```
curl -X POST -H "Content-Type: application/json" -d '{"message": "Merhaba"}' http://localhost:5000/chat
``` 