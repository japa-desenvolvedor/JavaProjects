package senai.projeto.pietro;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture backgroundTexture;
    private Texture bucketTexture;
    private Texture dropTexture;
    private Sound dropSound;
    private Music music;

    private FitViewport viewport;
    private Sprite bucketSprite;
    private Array<Sprite> dropSprites;
    private float dropTimer;

    private Rectangle bucketRect;
    private Vector2 touchPos;

    private static final float WORLD_WIDTH = 8f;
    private static final float WORLD_HEIGHT = 5f;
    private static final float BUCKET_SIZE = 1f;
    private static final float DROP_SIZE = 0.8f;
    private static final float BUCKET_SPEED = 4f;
    private static final float DROP_SPEED = 3f;

    @Override
    public void create() {
        System.out.println("=== INICIANDO JOGO ===");
        System.out.println("Plataforma: " + System.getProperty("os.name"));
        System.out.println("Diretório: " + System.getProperty("user.dir"));

        loadAssets();
        setupGame();

        System.out.println("✓ Jogo inicializado com sucesso!");
    }

    private void loadAssets() {
        try {
            // Carregar texturas
            backgroundTexture = new Texture(Gdx.files.internal("background.png"));
            bucketTexture = new Texture(Gdx.files.internal("bucket.png"));
            dropTexture = new Texture(Gdx.files.internal("drop.png"));

            // Carregar áudio
            dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav")); // Tente .wav também
            music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));

            System.out.println("✓ Assets carregados");

        } catch (Exception e) {
            System.err.println("❌ Erro ao carregar assets: " + e.getMessage());
            createFallbackAssets();
        }
    }

    private void createFallbackAssets() {
        System.out.println("Criando assets alternativos...");

        // Criar texturas simples programaticamente
        backgroundTexture = new Texture(32, 32, com.badlogic.gdx.graphics.Pixmap.Format.RGB888);
        bucketTexture = createColorTexture(Color.BLUE);
        dropTexture = createColorTexture(Color.CYAN);

        // Áudio opcional
        try {
            dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        } catch (Exception e) {
            System.out.println("Áudio não disponível");
        }
    }

    private Texture createColorTexture(Color color) {
        com.badlogic.gdx.graphics.Pixmap pixmap = new com.badlogic.gdx.graphics.Pixmap(32, 32, com.badlogic.gdx.graphics.Pixmap.Format.RGB888);
        pixmap.setColor(color);
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    private void setupGame() {
        batch = new SpriteBatch();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);

        // Configurar bucket
        bucketSprite = new Sprite(bucketTexture);
        bucketSprite.setSize(BUCKET_SIZE, BUCKET_SIZE);
        bucketSprite.setPosition(WORLD_WIDTH / 2 - BUCKET_SIZE / 2, 0.5f);

        dropSprites = new Array<>();
        dropTimer = 0;
        bucketRect = new Rectangle();
        touchPos = new Vector2();

        // Configurar música se disponível
        if (music != null) {
            music.setLooping(true);
            music.setVolume(0.3f);
            music.play();
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        handleInput();
        update();
        draw();
    }

    private void handleInput() {
        float delta = Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            bucketSprite.translateX(BUCKET_SPEED * delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            bucketSprite.translateX(-BUCKET_SPEED * delta);
        }

        if (Gdx.input.isTouched()) {
            viewport.unproject(touchPos.set(Gdx.input.getX(), Gdx.input.getY()));
            bucketSprite.setX(touchPos.x - BUCKET_SIZE / 2);
        }
    }

    private void update() {
        // Limitar movimento do bucket
        bucketSprite.setX(MathUtils.clamp(bucketSprite.getX(), 0, WORLD_WIDTH - BUCKET_SIZE));

        // Atualizar retângulo de colisão
        bucketRect.set(bucketSprite.getX(), bucketSprite.getY(), BUCKET_SIZE, BUCKET_SIZE);

        // Atualizar gotas
        float delta = Gdx.graphics.getDeltaTime();
        for (int i = dropSprites.size - 1; i >= 0; i--) {
            Sprite drop = dropSprites.get(i);
            drop.translateY(-DROP_SPEED * delta);

            if (drop.getY() < -DROP_SIZE) {
                dropSprites.removeIndex(i);
            } else if (bucketRect.contains(drop.getX() + DROP_SIZE/2, drop.getY() + DROP_SIZE/2)) {
                dropSprites.removeIndex(i);
                if (dropSound != null) dropSound.play();
            }
        }

        // Criar novas gotas
        dropTimer += delta;
        if (dropTimer > 0.5f) {
            dropTimer = 0;
            createDrop();
        }
    }

    private void createDrop() {
        Sprite drop = new Sprite(dropTexture);
        drop.setSize(DROP_SIZE, DROP_SIZE);
        drop.setPosition(MathUtils.random(0, WORLD_WIDTH - DROP_SIZE), WORLD_HEIGHT);
        dropSprites.add(drop);
    }

    private void draw() {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
        bucketSprite.draw(batch);
        for (Sprite drop : dropSprites) {
            drop.draw(batch);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        if (batch != null) batch.dispose();
        if (backgroundTexture != null) backgroundTexture.dispose();
        if (bucketTexture != null) bucketTexture.dispose();
        if (dropTexture != null) dropTexture.dispose();
        if (dropSound != null) dropSound.dispose();
        if (music != null) music.dispose();
    }
}
