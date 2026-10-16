# Why Stacks of 16? Reborn

Ender pearls, eggs, snowballs, signs, buckets, banners, armor stands, honey bottles and every other item that stacks to 16 stacks to 64 instead. Items from other mods that stack to 16 are included too.

Port of [Why Stacks of 16?](https://github.com/LaidBackSloth/WhyStacksOf16) by LaidBackSloth to Minecraft 1.21.1, for NeoForge and Fabric.

## Config

`config/wso16.json`:

```json
{
  "stackSize": 64,
  "exclude": ["minecraft:bucket"]
}
```

`stackSize` can be anything from 1 to 99. Items in `exclude` stay at 16. The server and every client need the same settings.

## Building

`./gradlew buildAll` builds both loaders and runs the game tests.

## License

MIT, same as the original. See [LICENSE](LICENSE).
